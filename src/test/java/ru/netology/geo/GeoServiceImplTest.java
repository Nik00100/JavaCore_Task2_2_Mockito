package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIp() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);

        Mockito.when(geoService.byIp(GeoServiceImpl.LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        assertEquals(geoService.byIp(GeoServiceImpl.LOCALHOST).toString(),
                new Location(null, null, null, 0).toString());

        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        assertEquals(geoService.byIp(GeoServiceImpl.MOSCOW_IP).toString(),
                new Location("Moscow", Country.RUSSIA, "Lenina", 15).toString());

        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP)).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        assertEquals(geoService.byIp(GeoServiceImpl.NEW_YORK_IP).toString(),
                new Location("New York", Country.USA, " 10th Avenue", 32).toString());

        Mockito.when(geoService.byIp("172.1.1.1")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        assertEquals(geoService.byIp("172.1.1.1").toString(),
                new Location("Moscow", Country.RUSSIA, null, 0).toString());

        Mockito.when(geoService.byIp("96.1.1.1")).thenReturn(new Location("New York", Country.USA, null,  0));
        assertEquals(geoService.byIp("96.1.1.1").toString(),
                new Location("New York", Country.USA, null,  0).toString());
    }
}