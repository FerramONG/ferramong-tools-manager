package ferramong.toolsmanager.services;

import ferramong.toolsmanager.entities.Tool;
import ferramong.toolsmanager.exceptions.ToolNotFoundException;
import ferramong.toolsmanager.repositories.ToolsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class ToolsManagerService {
    private final ToolsRepository toolsRepository;

    public Tool getTool(int toolId, int dwellerId) throws ToolNotFoundException {
        return toolsRepository.findByIdAndOwnerId(toolId, dwellerId)
                .orElseThrow(() -> new ToolNotFoundException(toolId));
    }

    public List<Tool> getAllTools(int dwellerId) {
        return toolsRepository.findAllByOwnerId(dwellerId);
    }

    @Transactional
    public Tool createTool(@NotNull Tool tool) {
        // TODO: Validate dwellerId.
        tool.setId(null);
        return toolsRepository.save(tool);
    }

    @Transactional
    public Tool updateTool(@NotNull Tool tool) throws ToolNotFoundException {
        var toolToUpdate = toolsRepository.findByIdAndOwnerId(tool.getId(), tool.getOwnerId());
        if (toolToUpdate.isPresent()) {
            return toolsRepository.save(tool);
        }

        throw new ToolNotFoundException(tool.getId());
    }

    @Transactional
    public Tool deleteTool(int toolId, int dwellerId) throws ToolNotFoundException {
        var deletedTool = toolsRepository.deleteByIdAndOwnerId(toolId, dwellerId);
        return deletedTool.orElseThrow(() -> new ToolNotFoundException(toolId));
    }

    @Transactional
    public List<Tool> deleteAllTools(int dwellerId) {
        return toolsRepository.deleteByOwnerId(dwellerId);
    }
}
