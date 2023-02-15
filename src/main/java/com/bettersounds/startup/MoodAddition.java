package com.bettersounds.startup;

import com.bettersounds.constant.DefaultMoods;
import com.bettersounds.domain.Mood;
import com.bettersounds.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author TEGA
 */
@Component
public class MoodAddition implements CommandLineRunner{
    
    @Autowired
    private MoodRepository moodRepository;
    
    @Override
    public void run(String... args) throws Exception {
        
//        DefaultMoods defaultMoods =  new DefaultMoods();
//        for(String mood: defaultMoods.getMood()){
//            
//            Mood m = new Mood();
//            m.setName(mood);
//            moodRepository.save(m);
//        }
        
    }
    
}
