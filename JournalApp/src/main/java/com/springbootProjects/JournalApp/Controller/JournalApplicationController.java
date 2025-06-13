package com.springbootProjects.JournalApp.Controller;

import com.springbootProjects.JournalApp.Entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Journal")
public class JournalApplicationController
{
    private Map<Long,JournalEntity> journalEntries= new HashMap<>();

    @GetMapping
    public List<JournalEntity> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean journalEntry(@RequestBody JournalEntity entry)
    {
        journalEntries.put(entry.getId(), entry);
        return true;
    }


    @GetMapping("id/{myId}")
    public JournalEntity getEntryById(@PathVariable Long myId)
    {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{id}")
    public JournalEntity delEntryById(@PathVariable Long id)
    {
        return journalEntries.remove(id);

    }

    @PutMapping("id/{id}")
    public JournalEntity updateById(@PathVariable Long id, @RequestBody JournalEntity Entry)
    {
        return journalEntries.put(id,Entry);
    }


}
