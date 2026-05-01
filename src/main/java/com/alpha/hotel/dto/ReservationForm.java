package com.alpha.hotel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ReservationForm {

    @NotBlank(message = "Le nom complet du client est obligatoire.")
    @Size(min = 2, max = 120, message = "Le nom complet doit contenir entre 2 et 120 caracteres.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ' -]+$", message = "Le nom complet ne doit contenir que des lettres, espaces, apostrophes ou tirets.")
    private String nomComplet;

    @Email(message = "Veuillez renseigner un email valide.")
    @NotBlank(message = "L'email est obligatoire.")
    private String email;

    @NotBlank(message = "Le telephone est obligatoire.")
    @Pattern(regexp = "^\\+?[0-9 ]{8,20}$", message = "Le telephone ne doit contenir que des chiffres, espaces et eventuellement un +.")
    private String telephone;

    @NotNull(message = "Veuillez choisir une chambre.")
    private Long chambreId;

    @NotNull(message = "La date d'arrivee est obligatoire.")
    @FutureOrPresent(message = "La date d'arrivee doit etre aujourd'hui ou future.")
    private LocalDate dateArrivee;

    @NotNull(message = "La date de depart est obligatoire.")
    @Future(message = "La date de depart doit etre future.")
    private LocalDate dateDepart;

    @NotBlank(message = "Le libelle de l'avance est obligatoire.")
    @Size(min = 3, max = 120, message = "Le libelle doit contenir entre 3 et 120 caracteres.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9'()\\- ,.]+$", message = "Le libelle contient des caracteres non autorises.")
    private String libelleAvance;

    @NotBlank(message = "Le mode de paiement de l'avance est obligatoire.")
    @Pattern(regexp = "^(MOBILE_MONEY|CHEQUE|CARTE_BANCAIRE|ESPECE)$", message = "Le mode de paiement de l'avance est invalide.")
    private String modePaiementAvance;

    @Pattern(regexp = "^$|^\\+?[0-9 ]{8,20}$", message = "Le numero Mobile Money ne doit contenir que des chiffres, espaces et eventuellement un +.")
    private String telephonePaiementMobile;

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getChambreId() {
        return chambreId;
    }

    public void setChambreId(Long chambreId) {
        this.chambreId = chambreId;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getLibelleAvance() {
        return libelleAvance;
    }

    public void setLibelleAvance(String libelleAvance) {
        this.libelleAvance = libelleAvance;
    }

    public String getModePaiementAvance() {
        return modePaiementAvance;
    }

    public void setModePaiementAvance(String modePaiementAvance) {
        this.modePaiementAvance = modePaiementAvance;
    }

    public String getTelephonePaiementMobile() {
        return telephonePaiementMobile;
    }

    public void setTelephonePaiementMobile(String telephonePaiementMobile) {
        this.telephonePaiementMobile = telephonePaiementMobile;
    }

    @AssertTrue(message = "Le numero du compte Mobile Money est obligatoire pour ce mode de paiement.")
    public boolean isTelephoneMobileMoneyValide() {
        if (!"MOBILE_MONEY".equals(modePaiementAvance)) {
            return true;
        }
        return telephonePaiementMobile != null && !telephonePaiementMobile.isBlank();
    }
}
