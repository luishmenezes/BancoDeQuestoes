    package com.example.BancoDeDados.Services;

    import com.example.BancoDeDados.Model.Estudante;
    import com.example.BancoDeDados.Model.Professor;
    import com.example.BancoDeDados.Repositores.ProfessorRepositores;

    import jakarta.validation.ConstraintValidatorContext;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @Service
    public class ServiceProfessor {

        @Autowired
        private ProfessorRepositores professorRepositores;

        @Autowired
        private EmailService emailService;

        @Transactional
        public Professor criar(Professor professor) {
            if (!validarSenha(professor.getSenha())) {
                throw new IllegalArgumentException(
                        "A senha não atende aos requisitos: mínimo de 8 caracteres, incluindo letras maiúsculas, minúsculas e números.");
            } else if (!validarEmail(professor.getEmail())) {
                throw new IllegalArgumentException(
                        "Email inválido.");
            }

            String assunto = "Confirmação de cadastro";
            String mensagem = String.format("Olá " + professor.getNome() + " obrigado por se cadastrar no nosso site! ");
            emailService.enviarEmail(professor.getEmail(), assunto, mensagem);

            return professorRepositores.save(professor);
        }

        public List<Professor> listar(Professor professor) {
            return professorRepositores.findAll();
        }

        public boolean deletar(Integer id) {
            try {
                if (professorRepositores.existsById(id)) {
                    professorRepositores.deleteById(id);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao deletar o professor: " + e.getMessage());
            }
        }

        public Optional<Professor> editar(Integer id) {
            try {
                return professorRepositores.findById(id);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao buscar o professor: " + e.getMessage());
            }
        }

        public boolean validarSenha(String senha) {
            if (senha.length() < 8) {
                return false;
            }
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;
            boolean hasDigit = false;

            for (char c : senha.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowerCase = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (hasUpperCase && hasLowerCase && hasDigit) {
                    return true;
                }
            }
            return false;
        }

        public boolean validarEmail(String email) {
            if (email == null) {
                return false;
            }

            int atIndex = email.indexOf('@');
            int dotIndex = email.lastIndexOf('.');

            if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1) {
                return true;
            } else {
                return false;
            }
        }

        public Map<String, Long> contarProfessoresPorInstituicao() {
            List<Professor> professores = professorRepositores.findAll();

            return professores.stream()
                    .collect(Collectors.groupingBy(Professor::getInstituicao, Collectors.counting()));
        }

        public Map<String, Long> contarProfessoresPorMaterias() {
            List<Professor> professores = professorRepositores.findAll();

            return professores.stream()
                    .collect(Collectors.groupingBy(Professor::getMateria1,  Collectors.counting()));
        }

        public Map<String, Long> contarProfessoresPorMaterias2() {
            List<Professor> professores = professorRepositores.findAll();

            return professores.stream()
                    .collect(Collectors.groupingBy(Professor::getMateria2,  Collectors.counting()));
        }


        public long contarProfessores() {
            return professorRepositores.count();
        }
    }