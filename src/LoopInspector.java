public class LoopInspector {

  public int loopSize(Node node) {
    Node beacon;
    Node onestep = node;
    Node twostep = node;
    while(true){
      onestep = onestep.getNext();
      twostep = twostep.getNext().getNext();
      if(onestep==twostep){
        beacon=onestep;
        break;
      }
    }
    int size = 0;
    onestep = beacon;
    while(true){
      onestep = onestep.getNext();
      size++;
      if(onestep==beacon){
        break;
      }
    }
    return size;
  }

}