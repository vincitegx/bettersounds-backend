package com.bettersounds.startup;

import com.bettersounds.constant.DefaultBeatKeys;
import com.bettersounds.domain.BeatKey;
import com.bettersounds.repository.BeatKeyRepository;
import com.bettersounds.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author TEGA
 */
@Component
public class BeatKeyAddition implements CommandLineRunner{
    
    @Autowired
    private BeatKeyRepository beatKeyRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
//        DefaultBeatKeys defaultBeatKeys =  new DefaultBeatKeys();
//        for(String defaultbeatKey: defaultBeatKeys.getBeatkey()){
//            
//            BeatKey beatKey = new BeatKey(); 
//            beatKey.setName(defaultbeatKey);
//            beatKeyRepository.save(beatKey);
//        }
    }
}
