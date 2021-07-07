package week10.parkingsystem;

import dagger.Component;

@Component
public interface PatternCreatorComponent {
    PatternCreator getPatternCreator();
}
