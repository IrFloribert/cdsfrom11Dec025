package bi.gov.otraco.ct.orientation.core.feign.validator.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record ValidationCommand(
        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}0-9 ()/'-]{2,50}$", message = "Ce champ doit contenir entre 2 et 50 lettres (espaces, apostrophes et tirets autorisés, mais pas de chiffres).")
        String receiptNo,

        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^[0-9]{3,20}$", message = "Tin No doit avoir 3 to 20 caracteres")
        String ownerTinNo,

        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,12}$", message = "PlateNo must have 6 to 12 characters, uppercase letters and numbers only")
        String plateNo,

        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}0-9 ()/'-]{2,50}$", message = "Ce champ doit contenir entre 2 et 50 lettres (espaces, apostrophes et tirets autorisés, mais pas de chiffres).")
        String chassisNo,

        @NotNull(message = "Ce champ est obligatoire")
        @Pattern(regexp = "^[A-Z0-9]{6,12}$", message = "User Code must have 6 to 12 characters, uppercase letters and numbers only")
        String user
) implements Serializable {}