file:///C:/Users/EbrimaMbye/Desktop/A_Teaching/lima-final-programming-II/unibank_api/src/main/java/com/unibank/api/advice/AuthenticationAdvice.java
empty definition using pc, found symbol in pc: 
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 272
uri: file:///C:/Users/EbrimaMbye/Desktop/A_Teaching/lima-final-programming-II/unibank_api/src/main/java/com/unibank/api/advice/AuthenticationAdvice.java
text:
```scala
clearpackage com.unibank.api.advice;

import com.unibank.api.commons.ApiErrorResponse;
import com.unibank.api.exceptions.InvalidJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.@@ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class AuthenticationAdvice {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentialException(HttpServletRequest request) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(Map.of("credentials", "Invalid username or password"))
                .message("Invalid username or password")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidJwtException(InvalidJwtException e, HttpServletRequest request) {
        ApiErrorResponse response = ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .errors(Map.of("jwt", e.getMessage()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 