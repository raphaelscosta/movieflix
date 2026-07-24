package com.movieflix.controller;


import com.movieflix.dto.request.StreamingRequestDTO;
import com.movieflix.dto.response.StreamingResponseDTO;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
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
    public ResponseEntity<StreamingResponseDTO> save(@RequestBody StreamingRequestDTO streaming){
        Streaming streamingSaved = streamingService.save(StreamingMapper.toEntity(streaming));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toResponse(streamingSaved));
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponseDTO>> findAll(){
        return ResponseEntity.ok(streamingService.findAll()
                .stream()
                .map(StreamingMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseDTO> findById(@PathVariable Long id){
        return streamingService.findById(id).map(
                streaming -> ResponseEntity.ok(StreamingMapper.toResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(streamingService.streamingExists(id)){
            streamingService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
