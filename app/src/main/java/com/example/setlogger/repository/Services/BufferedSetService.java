package com.example.setlogger.repository.Services;

import com.example.setlogger.repository.DatabaseContainer;
import com.example.setlogger.repository.dao.BufferedSetDAO;
import com.example.setlogger.repository.dto.BufferedSetDTO;
import com.example.setlogger.repository.entities.BufferedSet;

import java.util.ArrayList;
import java.util.List;

public class BufferedSetService implements BasicCRUDInterface<BufferedSetDTO> {

    BufferedSetDAO bufferedSetDAO;

    public BufferedSetService(DatabaseContainer databaseContainer) {
        bufferedSetDAO = databaseContainer.getDB().bufferedSetDAO();
    }

    @Override
    public List<BufferedSetDTO> getAll() {
        List<BufferedSet> list = bufferedSetDAO.getAll();
        return convertToDTOList(list);
    }

    @Override
    public BufferedSetDTO findById(int id) {
        return Converter.BufferedSetToDTO(bufferedSetDAO.findById(id));
    }

    @Override
    public void create(BufferedSetDTO toCreate) {
        bufferedSetDAO.insert(Converter.DTOToBufferedSet(toCreate));
    }

    @Override
    public void update(BufferedSetDTO toUpdate) {
        bufferedSetDAO.update(Converter.DTOToBufferedSet(toUpdate));
    }

    @Override
    public void delete(BufferedSetDTO toDelete) {
        bufferedSetDAO.delete(Converter.DTOToBufferedSet(toDelete));
    }

    private List<BufferedSetDTO> convertToDTOList(List<BufferedSet> bufferedSets) {
        List<BufferedSetDTO> bufferedSetDTOs = new ArrayList<BufferedSetDTO>();
        int i = 0;
        while (i < bufferedSets.size()) {
            bufferedSetDTOs.add(Converter.BufferedSetToDTO(bufferedSets.get(i)));
            i++;
        }
        return bufferedSetDTOs;
    }
}
