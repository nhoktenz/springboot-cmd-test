package com.example.myspringbootweb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PlaylistController {
    private List<Playlist> myPlaylist = new ArrayList();
    private final AtomicLong counter = new AtomicLong();
    public PlaylistController(){
        myPlaylist.add(new Playlist(counter.incrementAndGet(), "Ella_Fitzgerald - Tea For Two"));
    }
    @GetMapping(value = "/")
    public ResponseEntity index() {

    					System.out.println("+++++++++++++++++++ BucketListController GET ++++++++++++++++");


        return ResponseEntity.ok(myPlaylist);
    }
    @GetMapping(value = "/play_list")
    public ResponseEntity getplay_list(@RequestParam(value="id") Long id) {
        Playlist itemToReturn = null;
        for(Playlist play_list : myPlaylist){
            if(play_list.getId() == id)
                itemToReturn = play_list;
        }
        return ResponseEntity.ok(itemToReturn);
    }
    @PostMapping(value = "/")
    public ResponseEntity addToPlaylist(@RequestParam(value="name") String name) {
        myPlaylist.add(new Playlist(counter.incrementAndGet(), name));
        return ResponseEntity.ok(myPlaylist);
    }
    @PutMapping(value = "/")
    public ResponseEntity updatePlaylist(@RequestParam(value="name") String name, @RequestParam(value="id") Long id) {
        myPlaylist.forEach(Playlist ->  {
            if(Playlist.getId() == id){
                Playlist.setName(name);
            }
        });
        return ResponseEntity.ok(myPlaylist);
    }
    @DeleteMapping(value = "/")
    public ResponseEntity removePlaylist(@RequestParam(value="id") Long id) {
        Playlist itemToRemove = null;
        for(Playlist play_list : myPlaylist){
            if(play_list.getId() == id)
                itemToRemove = play_list;
        }
        myPlaylist.remove(itemToRemove);
        return ResponseEntity.ok(myPlaylist);
    }
}