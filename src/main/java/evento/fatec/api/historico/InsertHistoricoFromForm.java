package evento.fatec.api.historico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record InsertHistoricoFromForm(
        @NotBlank String nomeEmpresa,
        String cargo,
        LocalDate dataContratacao,
        String dataDesligamento,
        String funcao
) {
}
