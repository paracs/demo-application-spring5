package com.github.kazuki43zoo.api.date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

import javax.inject.Inject;

@RequestMapping("dates")
@RestController
@lombok.RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DateRestController {

    @lombok.NonNull
    private final JodaTimeDateFactory dateFactory;

    @RequestMapping(value = "currentDateTime", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public DateResource getCurrentTimeDate() {
        DateResource responseResource = new DateResource();
        responseResource.setDateTime(dateFactory.newDateTime());
        return responseResource;
    }

}
