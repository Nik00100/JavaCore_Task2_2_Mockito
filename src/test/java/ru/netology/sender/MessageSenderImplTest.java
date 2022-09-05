package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void sendRussianMessageWhenUsingRussianIP() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        Mockito.when(geoService.byIp("172.123.12.19")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        assertEquals("Добро пожаловать",messageSender.send(headers));
    }

    @Test
    void sendEnglishMessageWhenUsingUSAIP() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.1.1.1");

        Mockito.when(geoService.byIp("96.1.1.1")).thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        assertEquals("Welcome",messageSender.send(headers));
    }


}