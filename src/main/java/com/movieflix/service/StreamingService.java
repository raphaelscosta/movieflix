package com.movieflix.service;

import com.movieflix.dto.request.StreamingRequestDTO;
import com.movieflix.dto.response.StreamingResponseDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.repository.StreamingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository){
        this.streamingRepository = streamingRepository;
    }

    public StreamingResponseDTO saveCategory(StreamingRequestDTO streamingRequestDTO){
        Streaming streaming = StreamingMapper.toEntity(streamingRequestDTO);
        Streaming newStreaming = streamingRepository.save(streaming);
        return StreamingMapper.toResponse(newStreaming);
    }

    public List<StreamingResponseDTO> findAll(){
        return streamingRepository.findAll().stream().map(StreamingMapper::toResponse).toList();
    }

    public StreamingResponseDTO findById(Long id){
        Streaming streaming = streamingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Streaming not found"));

        return StreamingMapper.toResponse(streaming);
    }

    public void deleteById(Long id){
        Streaming streaming = streamingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Streaming not found"));

        streamingRepository.deleteById(streaming.getId());
    }
}
