package lk.ijse.dep.business.custom;

import lk.ijse.dep.business.SuperBO;
import lk.ijse.dep.dto.BatchDetDTO;

import java.util.List;

public interface ManageBatchesBO extends SuperBO {

    List<BatchDetDTO> getBatches() throws Exception;

    boolean CreateBatch(BatchDetDTO dto) throws Exception;

    boolean updateBatch(BatchDetDTO dto) throws Exception;

    boolean deleteBatch(String batchID) throws Exception;

    BatchDetDTO findBatch(String id) throws Exception;
}
