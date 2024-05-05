package dan.ms.tp.msusuarios.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.dto.AuthLoginDto;
import dan.ms.tp.msusuarios.dto.AuthRegisterDto;
import dan.ms.tp.msusuarios.dto.AuthTokenDto;
import dan.ms.tp.msusuarios.exception.ApiValidationException;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UsuarioJpaRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;
    private static final String JWT_SECRET = "SUPER_SECRET_DEFAULT_KEY_FOR_THIS_MS"; // ENV
    long EXPIRE_TIME = 3600 * 24 * 7; // secs

    @Override
    public AuthTokenDto login(AuthLoginDto dto) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findByUserName(dto.getUserName());

        if(!usuario.isPresent()){
            throw new Exception("Credenciales invalidas");
        }
        
        if(!usuario.get().getPassword().equals(dto.password)){
            throw new Exception("Credenciales invalidas");
        }

        String token = generateJwtToken(usuario.get());

        AuthTokenDto tokenResult = new AuthTokenDto(token);
        return tokenResult;
    }
    
    @Override
    public Usuario register(AuthRegisterDto dto) throws ApiValidationException{
        Usuario usuario = dto.toUser();

        TipoUsuario defaultTipoUsuario = new TipoUsuario();
        defaultTipoUsuario.setId(2); // TODO: |CHECKME| default user

        usuario.setTipoUsuario(defaultTipoUsuario);
        usuario = usuarioService.createDefaultFieldsUser(usuario);

        return usuario;
        // throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Boolean validate(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
            
            Claims c = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
            // valid
            return true;
        } catch (Exception e) {
            // invalid
            return false;
        }

    }

    private String generateJwtToken(Usuario usuario) throws Exception{
        try{

            if(usuario == null){
                throw new Exception("Algo salio mal al generar la sesion");
            }
            
            long expeTime = 1000 * EXPIRE_TIME; // in ms
            Date expDate = new Date(System.currentTimeMillis() + expeTime);
            
            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
            
            String token = Jwts.builder()
                .claim("sub", usuario.getUserName())
                .claim("tipo", usuario.getTipoUsuario())
                .setExpiration(expDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
            
            return token;
        }
        catch (Exception e){
            throw e;
        }
    }

}
