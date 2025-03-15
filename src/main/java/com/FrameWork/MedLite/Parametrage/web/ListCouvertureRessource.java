/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.MedLite.Parametrage.domaine.ListCouverture;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsListCouvertureOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.ListCouvertureDTO;
import com.FrameWork.MedLite.Parametrage.service.DetailsListCouvertureOperationService;
import com.FrameWork.MedLite.Parametrage.service.DetailsListCouvertureService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.ListCouvertureService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class ListCouvertureRessource {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    private final Logger log = LoggerFactory.getLogger(ListCouvertureRessource.class);

    @Autowired
    MessageSource messages;

    private final ListCouvertureService listCouvertureService;
    private final ParamService paramService;
    private final SocService societeService;
    private final DetailsListCouvertureService detailsListCouvertureService;

    private final DetailsListCouvertureOperationService detailsListCouvertureOperationService;

    private final AccessUserService accessUserService;

    public ListCouvertureRessource(ListCouvertureService listCouvertureService, ParamService paramService, SocService societeService, DetailsListCouvertureService detailsListCouvertureService, DetailsListCouvertureOperationService detailsListCouvertureOperationService, AccessUserService accessUserService) {
        this.listCouvertureService = listCouvertureService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.detailsListCouvertureService = detailsListCouvertureService;
        this.detailsListCouvertureOperationService = detailsListCouvertureOperationService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("list_couverture/{code}")
    public ResponseEntity<ListCouvertureDTO> getListCouvertureByCode(@PathVariable Integer code) {
        ListCouvertureDTO dTO = listCouvertureService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("list_couverture/prestation")
    public ResponseEntity<List<DetailsListCouvertureDTO>> getDetailsListCouvertureByCodeListCouverture(@RequestParam Integer codeListCouverture) {
        List<DetailsListCouvertureDTO> dTO = detailsListCouvertureService.findOneWithCodeListCouverture(codeListCouverture);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("list_couverture/prestation/findBy")
    public ResponseEntity<DetailsListCouvertureDTO> getDetailsListCouvertureByCodeListCouvertureByCodePrestaion(@RequestParam Integer codeListCouverture, @RequestParam Integer codePrestation,@RequestParam Integer codeNatureAdmission) {
//        List<DetailsListCouvertureDTO> dTO = ;
        return ResponseEntity.ok().body(detailsListCouvertureService.findOneWithCodeListCouvertureAndCodePrestationAndCodeNatureAdmission(codeListCouverture, codePrestation,codeNatureAdmission));
    }

    @GetMapping("list_couverture/operation")
    public ResponseEntity<List<DetailsListCouvertureOperationDTO>> getDetailsListCouvertureOperationByCodeListCouverture(@RequestParam Integer codeListCouverture) {
        List<DetailsListCouvertureOperationDTO> dTO = detailsListCouvertureOperationService.findOneWithCodeListCouvertureOperation(codeListCouverture);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("list_couverture/all")
    public ResponseEntity<List<ListCouvertureDTO>> getAllListCouverture() {
        return ResponseEntity.ok().body(listCouvertureService.findAllListCouverture());
    }

 
    @PostMapping("list_couvertures")
    public ResponseEntity<ListCouvertureDTO> saveListCouverture(@RequestBody ListCouvertureDTO dTO) {
        try {
            ListCouvertureDTO savedListCouverture = listCouvertureService.SaveListCouverture(dTO);
            return new ResponseEntity<>(savedListCouverture, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Replace with a proper logging mechanism in a production environment
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("list_couverture/findBy")
    public ResponseEntity<List<ListCouvertureDTO>> getAllListCouvertureByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(listCouvertureService.findAllListCouvertureByActif(actif));
    }

    @PutMapping("list_couverture/update")
    public ResponseEntity<ListCouvertureDTO> updateListCouverture(@Valid @RequestBody ListCouvertureDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ListCouvertureDTO result = listCouvertureService.updateNew(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("list_couverture/delete/{Code}")
    public ResponseEntity<ListCouverture> deleteListCouverture(@PathVariable("Code") Integer code) {
        listCouvertureService.deleteListCouverture(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("list_couverture/excelDetailsParTypeIntervenant")
//    public ResponseEntity<byte[]> excelDetailsParTypeIntervenant(@RequestParam Integer codeListCouverture) throws IOException, SQLException {
//        log.debug("Request to Export excelDetailListCouverture : {}");
//        List<EditionListCouvertureParTypeIntervenant> detailsParTypeIntervenants = listCouvertureService.findDetailsForEditionByCodeListCouverture(codeListCouverture);
//
//        Locale loc = LocaleContextHolder.getLocale();
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet(messages.getMessage("parametrage.ExcelDetailleeParDegree", null, loc));
//        sheet.getPrintSetup().setLandscape(true);
//        // Styles
//        XSSFCellStyle headerStyle = createHeaderStyle(workbook);
//
//        XSSFCellStyle CellStyleMontant = workbook.createCellStyle();
//        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
//            CellStyleMontant.setAlignment(HorizontalAlignment.LEFT);
//        } else {
//            CellStyleMontant.setAlignment(HorizontalAlignment.RIGHT);
//        }
//        CellStyleMontant.setFillForegroundColor(new XSSFColor(Color.WHITE));
//        CellStyleMontant.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//        XSSFCellStyle CellStylePrix = workbook.createCellStyle();
//        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
//            CellStylePrix.setAlignment(HorizontalAlignment.LEFT);
//        } else {
//            CellStylePrix.setAlignment(HorizontalAlignment.RIGHT);
//        }
//        CellStylePrix.setFillForegroundColor(new XSSFColor(Color.WHITE));
//        CellStylePrix.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//        int cellHeaderNum = 0;
//
//        // Header Row 0: Date, Username, Sheet Name
//        XSSFRow headerRow0 = sheet.createRow(0);
//        XSSFRow headerRow1 = sheet.createRow(1);
//
//        XSSFCell headerCell;
//
//        // Date
//        headerCell = headerRow0.createCell(0);
//        headerCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        headerCell.setCellStyle(headerStyle);
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
//
//        // Username (handle potential null)
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication != null ? authentication.getName() : "Unknown User";
//        headerCell = headerRow0.createCell(6);
//          sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 7));
//        headerCell.setCellValue("User: " + username);
//        headerCell.setCellStyle(headerStyle);
//      
//
//        // Centered sheet name (spanning multiple columns)
//        headerCell = headerRow1.createCell(0);
//        headerCell.setCellValue(messages.getMessage("parametrage.ExcelDetailleeParDegree", null, loc));
//        headerCell.setCellStyle(headerStyle);
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7)); // Merge cells for sheet name
//
//        // Header Row 1: Column Headers
//        XSSFRow headerRow3 = sheet.createRow(3);
//
//        //Header Cells
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.Famille", loc, 4000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.SousFamille", loc, 4000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.FamilleFacturation", loc, 4000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.codeSaisie", loc, 3000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.Designation", loc, 4000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.typeIntervenant", loc, 4000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.NatureAdmission", loc, 4000);
//        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.valeur", loc, 3000);
//
//        // Data Rows (starting from row 2)
//        int rownum = 4;
//        for (int i = 0; i < detailsParTypeIntervenants.size(); i++) {
//            XSSFRow row = sheet.createRow(rownum);
//            int cellnum = 0;
//            XSSFCell cell;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_famille_prestation());
//            cell.setCellStyle(CellStyleMontant);
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_sous_famille_prestation());
//            cell.setCellStyle(CellStyleMontant);
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignationFamilleFacturation());
//            cell.setCellStyle(CellStyleMontant);
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getCode_Saisie());
//            cell.setCellStyle(CellStyleMontant);
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_prestation());
//            cell.setCellStyle(CellStyleMontant);
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_Ar());
//            cell.setCellStyle(CellStyleMontant);
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_nature_admission());
//            cell.setCellStyle(CellStyleMontant);
//
//            cellnum++;
//
//            cell = row.createCell(cellnum);
//            cell.setCellValue(detailsParTypeIntervenants.get(i).getPrix().setScale(2, RoundingMode.HALF_UP).doubleValue());
//            cell.setCellStyle(CellStylePrix);
//            cellnum++;
//
//            rownum++;
//        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        workbook.write(baos);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
//        workbook.close();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
//        headers.setContentType(excelMediaType);
//        return ResponseEntity.ok().headers(headers).body(baos.toByteArray()); //Directly return the byte array
//    }
//
//    private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
//        XSSFCellStyle style = workbook.createCellStyle();
//        style.setFillForegroundColor(new XSSFColor(new Color(84, 148, 205)));
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setColor(new XSSFColor(new Color(255, 255, 255)));
//        style.setFont(font);
//        return style;
//    }
//
//    private XSSFCellStyle createDataStyle(XSSFWorkbook workbook, Locale loc) {
//        XSSFCellStyle style = workbook.createCellStyle();
//        style.setAlignment(loc.getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage()) ? HorizontalAlignment.LEFT : HorizontalAlignment.RIGHT);
//
//        XSSFFont font = workbook.createFont();
//        font.setFontName("Arial"); // Set font family
//        font.setFontHeightInPoints((short) 8); // Set font size
//
//        // Apply font to style
//        style.setFont(font);
//        style.setFillForegroundColor(new XSSFColor(new Color(255, 255, 255)));
//        return style;
//    }
//
//    private void addHeaderCell(XSSFRow row, int cellnum, XSSFCellStyle style, XSSFSheet sheet, String messageKey, Locale loc, int columnWidth) {
//        XSSFCell cell = row.createCell(cellnum);
//        cell.setCellStyle(style);
//        cell.setCellValue(messages.getMessage(messageKey, null, loc));
//        sheet.setColumnWidth(cellnum, columnWidth);
//    }
}
