package com.devjck.springboard.service.tracking;

import com.devjck.springboard.domain.tracking.Tracking;
import com.devjck.springboard.domain.tracking.TrackingRepository;
import com.devjck.springboard.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    @Transactional
    public Long save(Tracking tracking) {
        return trackingRepository.save(tracking).getTrackingId();
    }

    public String test(@RequestHeader MultiValueMap<String, String> header) {
        return header.toString();
    }

}
