package mate.academy.rickandmorty.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Status;
import mate.academy.rickandmorty.repository.StatusRepository;
import mate.academy.rickandmorty.service.StatusService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Status getByName(String name) {
        Status.StatusEnum existStatus = null;
        for (Status.StatusEnum currentStatus : Status.StatusEnum.values()) {
            if (currentStatus.getTitle().equalsIgnoreCase(name)) {
                existStatus = currentStatus;
                break;
            }
        }
        if (existStatus == null) {
            throw new RuntimeException("Send incorrect status");
        }

        Optional<Status> optionalStatus = statusRepository.findStatusByName(existStatus);
        if (optionalStatus.isPresent()) {
            return optionalStatus.get();
        }
        return statusRepository.save(new Status(existStatus));
    }
}
