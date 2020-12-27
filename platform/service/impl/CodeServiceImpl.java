package platform.service.impl;

import org.springframework.stereotype.Service;
import platform.model.Code;
import platform.service.CodeService;

import java.util.*;

//@Service
public class CodeServiceImpl implements CodeService {

    Deque<Code> deque = new LinkedList<>();

    @Override
    public Code[] getCode() {
        return deque.toArray(Code[]::new);
    }

    @Override
    public Code[] getLastCode() {
        return deque.stream().limit(10).toArray(Code[]::new);
    }

    @Override
    public Map<String, String> saveCode(Code code) {
        UUID id = UUID.randomUUID();
        code.setId(id);
        this.deque.addFirst(code);
        Map<String, String> map = new HashMap<>();
        map.put("id", code.getStringId());
        return map;
    }

    @Override
    public <S extends Code> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Code> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Code> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Iterable<Code> findAll() {
        return null;
    }

    @Override
    public Iterable<Code> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(Code entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Code> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Code> findAllByTimeAndViewsOrderByDateDesc(long time, int views) {
        return null;
    }

    @Override
    public List<Code> findTop10ByTimeAndViewsOrderByDateDesc(long time, int views) {
        return null;
    }

    @Override
    public Code getCodeById(UUID id) {
        return deque.stream().filter(e -> e.getId() == id).findFirst().get();
    }
}
