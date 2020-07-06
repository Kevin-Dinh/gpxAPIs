package com.example.demo;

import com.example.demo.config.Settings;
import com.example.demo.controller.GPXController;
import com.example.demo.core.ParsingException;
import com.example.demo.dto.GPXDto;
import com.example.demo.service.gpxService.ParseService;
import com.example.demo.service.gpxService.ParseServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

@RunWith(SpringRunner.class)
@DemoIntegrationTest
public class ParseTest {

    private MockMvc mockMvc;
    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private GPXController gpxController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(gpxController)
                .build();
    }

    @Test
    public void testTrackParsing() throws ParsingException, Exception {

        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        String fileName = "test.txt";
        File file = new File(System.getProperty("user.dir") + "/" + fileName);
        //delete if exits
        file.delete();
        File f = new File("sample/sample.gpx");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
                "text/plain", "test data".getBytes());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.fileUpload("api/uploadGPX")
                        .file(mockMultipartFile);
        this.mockMvc.perform(builder).andExpect(ok)
                .andDo(MockMvcResultHandlers.print());
    }
}
