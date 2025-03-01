/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.web;

import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.MedLite.Parametrage.domaine.PriceList;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListDTO;
import com.FrameWork.MedLite.Parametrage.dto.DetailsPriceListOperationDTO;
import com.FrameWork.MedLite.Parametrage.dto.EditionPriceListParTypeIntervenant;
import com.FrameWork.MedLite.Parametrage.dto.PriceListDTO;
import com.FrameWork.MedLite.Parametrage.service.DetailsPriceListOperationService;
import com.FrameWork.MedLite.Parametrage.service.DetailsPriceListService;
import com.FrameWork.MedLite.Parametrage.service.ParamService;
import com.FrameWork.MedLite.Parametrage.service.PriceListService;
import com.FrameWork.MedLite.Parametrage.service.SocService;
import com.FrameWork.MedLite.web.Util.Helper;

import org.apache.poi.ss.usermodel.HorizontalAlignment; //This is the correct import!

import jakarta.validation.Valid;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class PriceListRessource {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    private final Logger log = LoggerFactory.getLogger(PriceListRessource.class);

    @Autowired
    MessageSource messages;

    private final PriceListService priceListService;
    private final ParamService paramService;
    private final SocService societeService;
    private final DetailsPriceListService detailsPriceListService;

    private final DetailsPriceListOperationService detailsPriceListOperationService;

    private final AccessUserService accessUserService;

    public PriceListRessource(PriceListService priceListService, ParamService paramService, SocService societeService, DetailsPriceListService detailsPriceListService, DetailsPriceListOperationService detailsPriceListOperationService, AccessUserService accessUserService) {
        this.priceListService = priceListService;
        this.paramService = paramService;
        this.societeService = societeService;
        this.detailsPriceListService = detailsPriceListService;
        this.detailsPriceListOperationService = detailsPriceListOperationService;
        this.accessUserService = accessUserService;
    }

    @GetMapping("price_list/{code}")
    public ResponseEntity<PriceListDTO> getPriceListByCode(@PathVariable Integer code) {
        PriceListDTO dTO = priceListService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("price_list/prestation")
    public ResponseEntity<List<DetailsPriceListDTO>> getDetailsPriceListByCodePriceList(@RequestParam Integer codePriceList) {
        List<DetailsPriceListDTO> dTO = detailsPriceListService.findOneWithCodePriceList(codePriceList);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_price_list/findBy")
    public ResponseEntity<List<DetailsPriceListDTO>> getAllDetailsPriceListByCodePriceListAndCodePrestationAndCodeNatureAdmission(@RequestParam Integer codePriceList, @RequestParam Integer codePrestation, @RequestParam Integer codeNatureAdmission) {
        return ResponseEntity.ok().body(detailsPriceListService.findOneWithCodePriceListAndCodePrestationAndCodeNatureAdmission(codePriceList, codePrestation, codeNatureAdmission));
    }

    @GetMapping("price_list/operation")
    public ResponseEntity<List<DetailsPriceListOperationDTO>> getDetailsPriceListOperationByCodePriceList(@RequestParam Integer codePriceList) {
        List<DetailsPriceListOperationDTO> dTO = detailsPriceListOperationService.findOneWithCodePriceListOperation(codePriceList);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("price_list/societe")
    public ResponseEntity<List<PriceListDTO>> getPriceListByCodeSociete(@RequestParam Integer codeSociete) {
        List<PriceListDTO> dTO = priceListService.findAllPriceListByCodeSociete(codeSociete);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("price_list/all")
    public ResponseEntity<List<PriceListDTO>> getAllPriceList() {
        return ResponseEntity.ok().body(priceListService.findAllPriceList());
    }

    @PostMapping("price_lists")
    public ResponseEntity<PriceListDTO> savePriceList(@RequestBody PriceListDTO dTO) {
        try {
            PriceListDTO savedPriceList = priceListService.savepricelist(dTO);
            return new ResponseEntity<>(savedPriceList, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Replace with a proper logging mechanism in a production environment
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("price_list/findBy")
    public ResponseEntity<List<PriceListDTO>> getAllPriceListByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(priceListService.findAllPriceListByActif(actif));
    }

//        return this.http.get(`${environment.API_Parametrage}details_price_list/FindBy?codePrice=`+ codePriceList + `&codePrestation=`+codePrestation+ `&codeNatureAdmission=`+codeNatureAdmission);
    @PutMapping("price_list/update")
    public ResponseEntity<PriceListDTO> updatePriceList(@Valid @RequestBody PriceListDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PriceListDTO result = priceListService.updateNew(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("price_list/delete/{Code}")
    public ResponseEntity<PriceList> deletePriceList(@PathVariable("Code") Integer code) {
        priceListService.deletePriceList(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("price_list/excelDetailsParTypeIntervenant")
    public ResponseEntity<Resource> excelDetailsParTypeIntervenant(@RequestParam Integer codePriceList) throws IOException, SQLException {
        log.debug("Request to Export excelDetailPriceList : {}");
        List<EditionPriceListParTypeIntervenant> detailsParTypeIntervenants = priceListService.findDetailsForEditionByCodePriceList(codePriceList);

        PriceListDTO plData = priceListService.findOne(codePriceList);

        Locale loc = LocaleContextHolder.getLocale();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(messages.getMessage("parametrage.ExcelDetailleeParDegree", null, loc));
        sheet.getPrintSetup().setLandscape(true);
        // Styles
        XSSFCellStyle headerStyle = createHeaderStyle(workbook);

        XSSFCellStyle CellStyleMontant = workbook.createCellStyle();
        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
            CellStyleMontant.setAlignment(HorizontalAlignment.LEFT);
        } else {
            CellStyleMontant.setAlignment(HorizontalAlignment.RIGHT);
        }
        CellStyleMontant.setFillForegroundColor(new XSSFColor(Color.WHITE));
        CellStyleMontant.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle CellStylePrix = workbook.createCellStyle();
        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
            CellStylePrix.setAlignment(HorizontalAlignment.LEFT);
        } else {
            CellStylePrix.setAlignment(HorizontalAlignment.RIGHT);
        }
        CellStylePrix.setFillForegroundColor(new XSSFColor(Color.WHITE));
        CellStylePrix.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        int cellHeaderNum = 0;

        // Header Row 0: Date, Username, Sheet Name
        XSSFRow headerRow0 = sheet.createRow(0);
        XSSFRow headerRow1 = sheet.createRow(1);

        XSSFCell headerCell;

        // Date
        headerCell = headerRow0.createCell(0);
        headerCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        headerCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        // Username (handle potential null)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : "Unknown User";
        headerCell = headerRow0.createCell(6);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 7));
        headerCell.setCellValue("User: " + username);
        headerCell.setCellStyle(headerStyle);

        // Centered sheet name (spanning multiple columns)
        headerCell = headerRow1.createCell(0);
        headerCell.setCellValue(messages.getMessage(plData.getDesignationAr(), null, loc));
        headerCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7)); // Merge cells for sheet name

        // Header Row 1: Column Headers
        XSSFRow headerRow3 = sheet.createRow(3);

        //Header Cells
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.Famille", loc, 4000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.SousFamille", loc, 4000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.FamilleFacturation", loc, 4000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.codeSaisie", loc, 3000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.Designation", loc, 4000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.typeIntervenant", loc, 4000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.NatureAdmission", loc, 4000);
        addHeaderCell(headerRow3, cellHeaderNum++, headerStyle, sheet, "prestation.valeur", loc, 3000);

        // Data Rows (starting from row 2)
        int rownum = 4;
        for (int i = 0; i < detailsParTypeIntervenants.size(); i++) {
            XSSFRow row = sheet.createRow(rownum);
            int cellnum = 0;
            XSSFCell cell;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_famille_prestation());
            cell.setCellStyle(CellStyleMontant);
            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_sous_famille_prestation());
            cell.setCellStyle(CellStyleMontant);
            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignationFamilleFacturation());
            cell.setCellStyle(CellStyleMontant);
            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getCode_Saisie());
            cell.setCellStyle(CellStyleMontant);
            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_prestation());
            cell.setCellStyle(CellStyleMontant);
            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_Ar());
            cell.setCellStyle(CellStyleMontant);
            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getDesignation_nature_admission());
            cell.setCellStyle(CellStyleMontant);

            cellnum++;

            cell = row.createCell(cellnum);
            cell.setCellValue(detailsParTypeIntervenants.get(i).getPrix().setScale(2, RoundingMode.HALF_UP).doubleValue());
            cell.setCellStyle(CellStylePrix);
            cellnum++;

            rownum++;
        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        workbook.write(baos);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
//        workbook.close();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
//        headers.setContentType(excelMediaType);
//        return ResponseEntity.ok().headers(headers).body(baos.toByteArray()); //Directly return the byte array

//           ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        workbook.write(baos);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
//        workbook.close();
//        HttpHeaders headers = new HttpHeaders();
//        MediaType excelMediaType = new MediaType("application", "vnd.ms-excel");
//        headers.setContentType(excelMediaType);
//        return ResponseEntity.ok().headers(headers).body(Helper.read(byteArrayInputStream));
//        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        byte[] excelBytes = baos.toByteArray();
        baos.close(); //Important to close the stream
        workbook.close(); //Important to close the workbook
        ByteArrayResource resource = new ByteArrayResource(excelBytes);

        //Set correct content type and filename
        String filename = "Price List" + plData.getDesignationAr() + ".xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);

    }

    private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new Color(84, 148, 205)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(new XSSFColor(new Color(255, 255, 255)));
        style.setFont(font);
        return style;
    }

    private XSSFCellStyle createDataStyle(XSSFWorkbook workbook, Locale loc) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(loc.getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage()) ? HorizontalAlignment.LEFT : HorizontalAlignment.RIGHT);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial"); // Set font family
        font.setFontHeightInPoints((short) 8); // Set font size

        // Apply font to style
        style.setFont(font);
        style.setFillForegroundColor(new XSSFColor(new Color(255, 255, 255)));
        return style;
    }

    private void addHeaderCell(XSSFRow row, int cellnum, XSSFCellStyle style, XSSFSheet sheet, String messageKey, Locale loc, int columnWidth) {
        XSSFCell cell = row.createCell(cellnum);
        cell.setCellStyle(style);
        cell.setCellValue(messages.getMessage(messageKey, null, loc));
        sheet.setColumnWidth(cellnum, columnWidth);
    }
}
