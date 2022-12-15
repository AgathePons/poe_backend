package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.PoeDto;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Poe;
import survey.backend.entities.Trainee;
import survey.backend.repository.PoeRepository;

import java.util.Optional;

@Service
public class PoeService {

  @Autowired
  private PoeRepository poeRepository;

  public Iterable<Poe> findAll() {
    return this.poeRepository.findAll();
  }

  public Optional<Poe> findById(int id) {
    return this.poeRepository.findById((long) id);
  }

  public boolean delete(int id) {
    Optional<Poe> poeToDelete = this.poeRepository.findById((long) id);
    if (poeToDelete.isPresent()) {
      this.poeRepository.delete(poeToDelete.get());
      return true;
    }
    return false;
  }

  public Poe add(PoeDto poeDto) {
    return this.poeRepository.save(poeDto.toPoe());

  }

  public Optional<Poe> update(PoeDto poeDto) {
    Poe poe = poeDto.toPoe();
    Optional<Poe> oPoe = this.poeRepository.findById(poe.getId());
    if (oPoe.isPresent()) {
      this.poeRepository.save(poe);
      return Optional.of(poe);
    }
    return Optional.empty();
  }
}
