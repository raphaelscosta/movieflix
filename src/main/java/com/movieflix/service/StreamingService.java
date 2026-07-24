package com.movieflix.service;

import com.movieflix.entity.Streaming;
import com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository){
        this.streamingRepository = streamingRepository;
    }

    public Streaming save(Streaming newStreaming){
        return streamingRepository.save(newStreaming);

    }

    public boolean streamingExists(Long id){
        return streamingRepository.existsById(id);
    }

    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }

    public Optional<Streaming> findById(Long id){
        return streamingRepository.findById(id);
    }

    public List<Streaming> findAllById(List<Long> streamingIds){
        return streamingRepository.findAllById(streamingIds);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }
}
