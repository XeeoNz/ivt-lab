package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import javax.naming.spi.DirStateFactory.Result;

public class GT4500Test {

  private GT4500 ship;

  TorpedoStore primary;
  TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);

    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    
    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);

  }



  // 5 db teszt
  //
  //
  @Test
  public void fireTorpedo_SINGLE_Both_Empty() {
    //Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    

    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(false, result);
    verify(primary,times(0)).fire(1);
    verify(secondary,times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_Both_Empty() {
    //Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
   

    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(false, result);
    verify(primary,times(0)).fire(1);
    verify(secondary,times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_ALL_Primary_Empty_Secondary_Fires() {
    //Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);
   

    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(true, result);
    verify(primary,times(0)).fire(1);
    verify(secondary,times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_SINGLE_Primary_Fires_Then_Secondary_Fires() {
    //Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
   

    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
  }


  @Test
  public void fireTorpedo_SINGLE_Primary_Fires_Then_Secondary_Empty_So_Primary_Fires_Again() {
    //Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    
   

    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    verify(primary,times(2)).fire(1);
    verify(secondary,times(0)).fire(1);
  }



  // Kodsorbol egy teszteset.
  @Test
  public void fireTorpedo_SINGLE_Primary_Fires_Then_Both_Empty() {
    //Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    
   

    //Act
    ship.fireTorpedo(FiringMode.SINGLE);

    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);

    ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    
    verify(primary,times(1)).fire(1);
    verify(secondary,times(0)).fire(1);
  }


  @Test
  public void fireTorpedo_Switch() {
    //Arrange
    
   

    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(false, result);
    
  }


 // 100%-os lefedettseg
 @Test
 public void fire_Laser() {
   //Arrange
   
   
  

   //Act
   boolean result = ship.fireLaser(FiringMode.SINGLE);

   //Assert
   assertEquals(false, result);
   
 }

 


}
