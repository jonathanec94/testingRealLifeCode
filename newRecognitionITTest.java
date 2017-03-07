package net.sf.javaanpr.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.xml.sax.SAXException;

/**
 *
 * @author Jonathan
 */
@RunWith(JUnitParamsRunner.class)
public class newRecognitionITTest {

    @Test
    @FileParameters("src/test/resources/testInput.csv")
    public void testAllSnapshots(String path, String expectedResult) throws IOException, ParserConfigurationException, SAXException {
        CarSnapshot carSnap = new CarSnapshot("snapshots/" + path);   
        assertThat("carSnap is null", carSnap, notNullValue());
        assertThat("carSnap.image is null", carSnap.getImage(), notNullValue());
        
        Intelligence intel = new Intelligence();
        assertThat(intel, is(notNullValue()));
        
        String spz = intel.recognize(carSnap);
        assertThat("The licence plate is null - are you sure the image has the correct color space?", spz, notNullValue());
        assertThat(spz, equalTo(expectedResult));
        
        carSnap.close();
    }
}
