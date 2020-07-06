package com.example.demo.service.gpxService;

import com.example.demo.core.ParsingException;
import com.example.demo.domain.GPX;
import com.example.demo.dto.GPXDto;
import com.example.demo.dto.response.GPXResponseDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Validated
@Transactional(rollbackFor = Throwable.class)
public interface ParseService {

    /**
     * Parses a GPX file and returns the resulting GPX track collection
     *
     * @param file the file to be parsed
     * @return the resulting track collection
     * @throws IOException,
     * @throws JAXBException,
     * @throws XMLStreamException
     */
    GPXDto parseFile(MultipartFile file) throws JAXBException, XMLStreamException, IOException;

    GPXDto parseFile(File file) throws JAXBException, XMLStreamException, IOException;

    /**
     * Get details and return the resulting GPX track collection to user
     *
     * @param userId the file to be parsed
     * @return the resulting track collection
     */
    GPXResponseDto getDetails(int userId);


}
