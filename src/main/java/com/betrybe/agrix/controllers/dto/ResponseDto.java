package com.betrybe.agrix.controllers.dto;

/**
 * The type Response dto.
 *
 * @param <T> the type parameter
 */
public record ResponseDto<T>(String message, T data) {

}
