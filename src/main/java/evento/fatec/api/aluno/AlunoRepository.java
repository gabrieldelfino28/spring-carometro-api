package evento.fatec.api.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AlunoRepository extends JpaRepository <Aluno,Long> {
    public Aluno findAlunoByra(@Param("ra_aluno") String ra);
}
