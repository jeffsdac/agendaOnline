package br.com.fiap.epicontacts.contact;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Contact {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String contname;

    @Pattern(regexp="[0-9]{8,9}", message="O número de telefone deve ter 8 ou 9 dígitos")
    String contnumber;

    
}
