/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.factory;
 
import com.FrameWork.MedLite.Parametrage.domaine.ResponsableRemise;
import com.FrameWork.MedLite.Parametrage.domaine.SignatureMedecin;
import com.FrameWork.MedLite.Parametrage.dto.ResponsableRemiseDTO;
import com.FrameWork.MedLite.Parametrage.dto.SignatureMedecinDTO; 
import static com.FrameWork.MedLite.Parametrage.factory.ResponsableRemiseFactory.responsableRemiseToResponsableRemiseDTO;
import jakarta.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author Administrator
 */
public class SignatureMedecinFactory {
    public static SignatureMedecin accessSignatureMedecinDTOToSignatureMedecinx(SignatureMedecinDTO dto, SignatureMedecin domaine) {
        if (dto != null) {

            domaine.setCode(dto.getCode()); 
 
              domaine.setCodeMedecin(dto.getCodeMedecin());
            if (domaine.getCodeMedecin() != null) {
                domaine.setMedecin(MedecinFactory.createMedecinByCode(dto.getCodeMedecin()));
            }

            
            

            if (dto.getSig() != null) {

                String[] strings = dto.getSig().split(",");
                String extension;
                switch (strings[0]) {//check image's extension
                    case "data:image/jpeg;base64":
                        extension = "jpeg";
                        break;
                    case "data:image/png;base64":
                        extension = "png";
                        break;
                    case "data:application/pdf;base64":
                        extension = "pdf";
                        break;
                    case "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
                        extension = "docx";
                        break;
                    default://should write cases for more images types
                        extension = "jpg";
                        break;
                }
                //convert base64 string to binary data
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);

                domaine.setSignature(data);
            } else {
                domaine.setSignature(null);
            }

            return domaine;
        } else {
            return null;
        }
    }
    
    
    public static SignatureMedecinDTO signatureMedecinTosignatureMedecinDTO(SignatureMedecin domaine) {

        if (domaine != null) {
            SignatureMedecinDTO dto = new SignatureMedecinDTO();
            dto.setCode(domaine.getCode());    
            dto.setDateCreate(domaine.getDateCreate()); 
            dto.setUserCreate(domaine.getUserCreate());


 
            dto.setSignature(domaine.getSignature());

         
            dto.setMedecinDTO(MedecinFactory.medecinToMedecinDTO(domaine.getMedecin()));
            dto.setCodeMedecin(domaine.getCodeMedecin());

         
            return dto;
        } else {
            return null;
        }
    }
    
     public static List<SignatureMedecinDTO> listSignatureMedecinToSignatureMedecinDTOs(List<SignatureMedecin> signatureMedecins) {
        List<SignatureMedecinDTO> list = new ArrayList<>();
        for (SignatureMedecin signatureMedecin : signatureMedecins) {
            list.add(signatureMedecinTosignatureMedecinDTO(signatureMedecin));
        }
        return list;
    }
     

    
}
