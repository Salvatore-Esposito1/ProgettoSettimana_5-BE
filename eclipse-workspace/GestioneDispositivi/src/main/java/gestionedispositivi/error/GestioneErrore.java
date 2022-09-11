package gestionedispositivi.error;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GestioneErrore extends ResponseEntityExceptionHandler {


	@ExceptionHandler(EntityExistsException.class)
	protected ResponseEntity<String> gestisciEntityExistException(EntityExistsException e){
		log.info("Errore gestito da GestioneErrore");
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.FOUND);
	}
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<String> gestisciEntityNotFoundException(EntityNotFoundException e) {
		log.info("Errore gestito da GestioneErrore");
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
