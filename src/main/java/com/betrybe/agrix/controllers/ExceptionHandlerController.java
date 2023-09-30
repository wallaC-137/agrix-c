package com.betrybe.agrix.controllers;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Exception handler controller.
 */
@ControllerAdvice
public class ExceptionHandlerController {

  /**
   * Handler farm not found response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handlerFarmNotFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  /**
   * Handler crop not found response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handlerCropNotFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  /**
   * Handler fertilizer not found response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handlerFertilizerNotFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }

  /**
   * Handler sql integrity constraint violation exception response entity.
   *
   * @return the response entity
   */
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<String> handlerSqlIntegrityConstraintViolationException() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Nome de usuário já cadastrado!");
  }
}
