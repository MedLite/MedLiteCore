/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Parametrage.domaine;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Embeddable
public class DetailsListCouvertureOperationPK {

    @Column(name = "Code_ListCouverture")
    private int codeListCouverture;
    @Column(name = "Code_Operation")
    private int codeOperation; 

    public DetailsListCouvertureOperationPK(int codeListCouverture, int codeOperation) {
        this.codeListCouverture = codeListCouverture;
        this.codeOperation = codeOperation;
    }

    public int getCodeListCouverture() {
        return codeListCouverture;
    }

    public void setCodeListCouverture(int codeListCouverture) {
        this.codeListCouverture = codeListCouverture;
    }

    public int getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(int codeOperation) {
        this.codeOperation = codeOperation;
    }

    

}
