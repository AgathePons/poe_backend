package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.entities.Poe;
import survey.backend.repository.PoeRepository;

@Service
public class PoeService {

  @Autowired
  private PoeRepository poeRepository;

  public Iterable<Poe> findAll() {
    return this.poeRepository.findAll();
  }
}
