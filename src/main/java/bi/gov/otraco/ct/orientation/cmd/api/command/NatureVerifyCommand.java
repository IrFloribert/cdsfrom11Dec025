package bi.gov.otraco.ct.orientation.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


@Schema(name = "Nature Verify Command", description = "Commande pour mettre à jour les informations d'une nature de véhicule")
public record NatureVerifyCommand(


)implements Serializable{
}
