package com.spinningback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spinningback.DTO.UsuarioDTO;
import com.spinningback.models.Usuario;
import com.spinningback.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

    @Operation(
        summary = "Cria um novo usuário",
        description = "Clique em **Try it out** para testar. Para criar um usuário, preencha todos os campos obrigatórios e clique em Execute. O ID gerado será utilizado nos endpoints de atualização, busca e deleção."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(value = "{\"id\":1,\"nomeCompleto\":\"Beatrice Oliveira Gomes\",\"email\":\"BeatrizOliveiraGomes@dayrep.com\",\"senha\":\"$2a$10$...\",\"telefone\":\"(44) 4850-8628\",\"cpf\":\"907.157.038-03\",\"dataDeNascimento\":\"1963-01-03\"}")
        }))
    })
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(
            @Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuario);
    }

    @Operation(
        summary = "Busca um usuário por ID",
        description = "Clique em **Try it out** para testar. Para buscar um usuário, informe o ID de um usuário previamente criado pelo endpoint POST /usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUmUsuario(
            @PathVariable("id") Long id) {
        Usuario usuario = usuarioService.buscarUmUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(
        summary = "Lista todos os usuários",
        description = "Clique em **Try it out** para listar todos os usuários cadastrados no banco de dados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Todos os usuários", content = @Content(mediaType = "application/json", array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = Usuario.class))))
    })
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
    }

	@Operation(
        summary = "Atualiza um usuário",
        description = "Clique em **Try it out** para testar. Para atualizar um usuário, primeiro crie um usuário usando o endpoint POST /usuario. Depois, utilize o ID retornado na criação para preencher o campo {id} deste endpoint.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de requisição para editar um usuário",
            required = true,
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\n  \"nomeCompleto\": \"Beatrice Oliveira Gomes\",\n  \"email\": \"BeatrizOliveiraGomes@dayrep.com\",\n  \"senha\": \"maej0ooHi5\",\n  \"confirmacaoDeSenha\": \"maej0ooHi5\",\n  \"telefone\": \"(44) 4850-8628\",\n  \"cpf\": \"907.157.038-03\",\n  \"dataDeNascimento\": \"1963-01-03\"\n}"
                )
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable("id") Long id,
            @Valid @RequestBody Usuario usuarioAtualizado) {
        Usuario atualizado = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
        summary = "Deleta um usuário",
        description = "Clique em **Try it out** para testar. Para deletar um usuário, informe o ID de um usuário previamente criado pelo endpoint POST /usuario.",
		parameters = @Parameter(
			name = "id",
			description = "ID do usuário a ser deletado",
			required = true,
			example = "1")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
