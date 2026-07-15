package com.movieflix.controller;


import com.movieflix.dto.request.StreamingRequestDTO;
import com.movieflix.dto.response.CategoryResponseDTO;
import com.movieflix.dto.response.StreamingResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService){
        this.streamingService = streamingService;
    }

    @PostMapping
    public ResponseEntity<StreamingResponseDTO> saveCategory(@RequestBody StreamingRequestDTO streaming){
        StreamingResponseDTO categoryResponseDTO = streamingService.saveCategory(streaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponseDTO>> getAllCategories(){
        List<StreamingResponseDTO> all = streamingService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseDTO> getCategoryById(@PathVariable Long id){
        StreamingResponseDTO streamingResponseDTO = streamingService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(streamingResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        streamingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
