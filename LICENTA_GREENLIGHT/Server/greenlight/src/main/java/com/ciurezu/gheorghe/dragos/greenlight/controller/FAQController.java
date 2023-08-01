package com.ciurezu.gheorghe.dragos.greenlight.controller;

import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.response.GreenLightResponse;
import com.ciurezu.gheorghe.dragos.greenlight.data.model.shared.FAQDTO;
import com.ciurezu.gheorghe.dragos.greenlight.exception.BadRequestException;
import com.ciurezu.gheorghe.dragos.greenlight.exception.ConflictException;
import com.ciurezu.gheorghe.dragos.greenlight.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faq")
public class FAQController {
    private final FAQService faqService;

    @GetMapping("/get-all-faq")
    public ResponseEntity<GreenLightResponse<?>> getAllFAQ() {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(faqService.getAllFAQ(),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PostMapping("/add-faq")
    public ResponseEntity<GreenLightResponse<?>> addFAQ(@RequestBody FAQDTO faqDTO) throws ConflictException, BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(faqService.save(faqDTO),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @PutMapping("/edit-faq")
    public ResponseEntity<GreenLightResponse<?>> updateFAQ(@RequestBody List<FAQDTO> faqdtos) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(faqService.updateFAQ(faqdtos),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }

    @DeleteMapping("/delete-faq")
    public ResponseEntity<GreenLightResponse<?>> deleteFAQ(@RequestParam("title") String title) throws BadRequestException {
        GreenLightResponse<?> greenLightResponse = new GreenLightResponse<>(faqService.deleteFAQ(title),"success");
        return ResponseEntity.ok().body(greenLightResponse);
    }
}
