//package com.axis.team6.coderiders.sharemytrip.farecalculationservice.service;
//
//import com.axis.team6.coderiders.sharemytrip.farecalculationservice.exception.InvalidLocationException;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class GoogleMapsServiceTest {
//
//    @Mock
//    private OkHttpClient okHttpClient;
//
//    @InjectMocks
//    private GoogleMapsService googleMapsService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        ReflectionTestUtils.setField(googleMapsService, "apiKey", "fakeApiKey");
//    }
//
//    @Test
//    void getDistance() throws IOException {
//        String jsonResponse = "{ 'rows': [ { 'elements': [ { 'distance': { 'value': 10000 } } ] } ] }";
//        Response response = mock(Response.class);
//        ResponseBody responseBody = mock(ResponseBody.class);
//
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.body()).thenReturn(responseBody);
//        when(responseBody.string()).thenReturn(jsonResponse);
//
//        OkHttpClient client = mock(OkHttpClient.class);
//        when(client.newCall(any(Request.class)).execute()).thenReturn(response);
//
//        ReflectionTestUtils.setField(googleMapsService, "okHttpClient", client);
//
//        float distance = googleMapsService.getDistance("fromCoordinates", "toCoordinates");
//        assertEquals(10.0f, distance);
//    }
//
//    @Test
//    void getDistanceWithInvalidLocation() throws IOException {
//        String jsonResponse = "{ 'rows': [ { 'elements': [ { 'status': 'NOT_FOUND' } ] } ] }";
//        Response response = mock(Response.class);
//        ResponseBody responseBody = mock(ResponseBody.class);
//
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.body()).thenReturn(responseBody);
//        when(responseBody.string()).thenReturn(jsonResponse);
//
//        OkHttpClient client = mock(OkHttpClient.class);
//        when(client.newCall(any(Request.class)).execute()).thenReturn(response);
//
//        ReflectionTestUtils.setField(googleMapsService, "okHttpClient", client);
//
//        assertThrows(InvalidLocationException.class, () -> googleMapsService.getDistance("fromCoordinates", "toCoordinates"));
//    }
//
//    @Test
//    void getCoordinates() throws IOException {
//        String jsonResponse = "{ 'results': [ { 'geometry': { 'location': { 'lat': 40.712776, 'lng': -74.005974 } } } ] }";
//        Response response = mock(Response.class);
//        ResponseBody responseBody = mock(ResponseBody.class);
//
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.body()).thenReturn(responseBody);
//        when(responseBody.string()).thenReturn(jsonResponse);
//
//        OkHttpClient client = mock(OkHttpClient.class);
//        when(client.newCall(any(Request.class)).execute()).thenReturn(response);
//
//        ReflectionTestUtils.setField(googleMapsService, "okHttpClient", client);
//
//        String coordinates = googleMapsService.getCoordinates("New York");
//        assertEquals("40.712776,-74.005974", coordinates);
//    }
//
//    @Test
//    void getCoordinatesWithInvalidLocation() throws IOException {
//        String jsonResponse = "{ 'results': [] }";
//        Response response = mock(Response.class);
//        ResponseBody responseBody = mock(ResponseBody.class);
//
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.body()).thenReturn(responseBody);
//        when(responseBody.string()).thenReturn(jsonResponse);
//
//        OkHttpClient client = mock(OkHttpClient.class);
//        when(client.newCall(any(Request.class)).execute()).thenReturn(response);
//
//        ReflectionTestUtils.setField(googleMapsService, "okHttpClient", client);
//
//        assertThrows(InvalidLocationException.class, () -> googleMapsService.getCoordinates("InvalidLocation"));
//    }
//
//    @Test
//    void getDuration() throws IOException {
//        String jsonResponse = "{ 'rows': [ { 'elements': [ { 'duration': { 'value': 3600 } } ] } ] }";
//        Response response = mock(Response.class);
//        ResponseBody responseBody = mock(ResponseBody.class);
//
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.body()).thenReturn(responseBody);
//        when(responseBody.string()).thenReturn(jsonResponse);
//
//        OkHttpClient client = mock(OkHttpClient.class);
//        when(client.newCall(any(Request.class)).execute()).thenReturn(response);
//
//        ReflectionTestUtils.setField(googleMapsService, "okHttpClient", client);
//
//        String duration = googleMapsService.getDuration("fromCoordinates", "toCoordinates");
//        assertEquals("1:00:00", duration);
//    }
//
//    @Test
//    void getDurationWithInvalidLocation() throws IOException {
//        String jsonResponse = "{ 'rows': [ { 'elements': [ { 'status': 'NOT_FOUND' } ] } ] }";
//        Response response = mock(Response.class);
//        ResponseBody responseBody = mock(ResponseBody.class);
//
//        when(response.isSuccessful()).thenReturn(true);
//        when(response.body()).thenReturn(responseBody);
//        when(responseBody.string()).thenReturn(jsonResponse);
//
//        OkHttpClient client = mock(OkHttpClient.class);
//        when(client.newCall(any(Request.class)).execute()).thenReturn(response);
//
//        ReflectionTestUtils.setField(googleMapsService, "okHttpClient", client);
//
//        assertThrows(InvalidLocationException.class, () -> googleMapsService.getDuration("fromCoordinates", "toCoordinates"));
//    }
//}
