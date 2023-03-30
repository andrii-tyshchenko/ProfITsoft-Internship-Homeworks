package dev.profitsoft.reader.errorDecoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new ResponseStatusException(HttpStatus.BAD_REQUEST);
            case 401 -> new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            case 403 -> new ResponseStatusException(HttpStatus.FORBIDDEN);
            case 404 -> new ResponseStatusException(HttpStatus.NOT_FOUND);
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}