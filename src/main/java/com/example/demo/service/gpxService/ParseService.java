package com.example.demo.service.gpxService;

import com.example.demo.core.ParsingException;
import com.example.demo.domain.GPX;
import org.jdom.Document;

import java.io.File;
import java.net.URL;

public interface ParseService {
    /**
     * Parses a GPX file and returns the resulting GPX track collection
     *
     * @param file the file to be parsed
     * @return the resulting track collection
     * @throws ParsingException
     */
    public abstract GPX parse(File file) throws ParsingException;

    /**
     * Parses a GPX URL and returns the resulting GPX track collection
     *
     * @param url the URL to be parsed
     * @return the resulting track collection
     * @throws ParsingException
     */
    public abstract GPX parse(URL url) throws ParsingException;
}
