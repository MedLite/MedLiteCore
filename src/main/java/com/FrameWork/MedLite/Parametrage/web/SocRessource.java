/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Authentification.dto.AccessUserDTO;
import com.FrameWork.MedLite.Parametrage.dto.SocDTO;
import com.FrameWork.MedLite.Parametrage.dto.SpecialiteCabinetDTO;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RequestMapping("/api/soc")
@RestController
public class SocRessource {

    private final SocService socService;

    public SocRessource(SocService socService) {
        this.socService = socService;
    }

    @GetMapping("/logos/{code}")
    public ResponseEntity<SocDTO> getLogo(@PathVariable Integer code) {
        SocDTO dto = socService.findOneWithLogo(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/logo/{code}")
    public ResponseEntity<Resource> getSignature(@PathVariable Integer code) {
        SocDTO dto = socService.findOne(code);
        if (dto == null || dto.getLogo() == null) {
            return ResponseEntity.notFound().build(); // Handle missing logo
        }

        String mimeType = determineMimeType(dto.getLogo()); // Implement this method
        Resource resource = new ByteArrayResource(dto.getLogo());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"logo.png\"") // or appropriate extension
                .body(resource);
    }

//Helper function to determine MIME type
    private String determineMimeType(byte[] data) {
        // Use a library like Apache Tika or similar to determine MIME type accurately.
        // If no suitable library is available, use a simple heuristic based on file extension
        // or magic numbers (first few bytes) for common image formats.
        // This is a simplified example and may not be accurate for all image types.
        if (data.length >= 4 && data[0] == (byte) 0x89 && data[1] == (byte) 0x50 && data[2] == (byte) 0x4E && data[3] == (byte) 0x47) {
            return "image/png";
        } else if (data.length >= 2 && data[0] == (byte) 0xFF && data[1] == (byte) 0xD8) {
            return "image/jpeg";
        } else {
            return "application/octet-stream"; // fallback
        }
    }

}
