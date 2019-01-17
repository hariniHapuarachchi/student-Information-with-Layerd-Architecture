package lk.ijse.dep.business.custom.impl;

import lk.ijse.dep.business.Converter;
import lk.ijse.dep.business.custom.ManageBatchesBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.BatchDetDAO;
import lk.ijse.dep.dto.BatchDetDTO;

import java.util.List;

public class ManageBatchesBOImpl implements ManageBatchesBO {

    private BatchDetDAO batchDetDAO;

    public ManageBatchesBOImpl() {
        batchDetDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BATCH);
    }

    @Override
    public List<BatchDetDTO> getBatches() throws Exception {
        return batchDetDAO.findAll().map(Converter::<BatchDetDTO>getDTOList).get();
    }

    @Override
    public boolean CreateBatch(BatchDetDTO dto) throws Exception {
        return batchDetDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateBatch(BatchDetDTO dto) throws Exception {
        return batchDetDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteBatch(String batchID) throws Exception {
        return batchDetDAO.delete(batchID);
    }

    @Override
    public BatchDetDTO findBatch(String id) throws Exception {
        return batchDetDAO.find(id).map(Converter::<BatchDetDTO>getDTO).orElse(null);
    }

}
