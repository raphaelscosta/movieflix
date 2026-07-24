package com.movieflix.mapper;

import com.movieflix.dto.request.StreamingRequestDTO;
import com.movieflix.dto.response.StreamingResponseDTO;
import com.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toEntity(StreamingRequestDTO streamingRequestDTO){
        Streaming streaming = new Streaming();
        streaming.setName(streamingRequestDTO.name());
        return streaming;
    }

    public static StreamingResponseDTO toResponse(Streaming streaming){

        return new StreamingResponseDTO(
                streaming.getId(),
                streaming.getName()
        );
    }
}
