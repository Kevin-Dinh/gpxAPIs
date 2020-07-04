package com.example.demo;

import com.example.demo.core.ParsingException;
import com.example.demo.domain.GPX;
import com.example.demo.service.gpxService.ParseService;
import com.example.demo.service.gpxService.ParseServiceImp;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ParseTest {
    @Test
    public void testTrackParsing() throws ParsingException {
        ParseService parser = new ParseServiceImp();
        GPX gpx = parser.parse(new File("sample/sample.gpx"));

        assertEquals(1, gpx.getTracks().size());

        assertTrue(gpx.getTracks().get(0).cumulativeAscent() > 0);
        assertTrue(gpx.getTracks().get(0).cumulativeDescent() > 0);
        assertTrue(gpx.getTracks().get(0).length() > 0);

        assertNotNull(gpx.getTracks().get(0).startingTime());
        assertNotNull(gpx.getTracks().get(0).endTime());
    }
}
