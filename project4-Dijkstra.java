    public void computeAllEuclideanDistances() {
      for(Vertex v: vertices.values()){
          List<Edge<V>> listOfEdges = v.getEdges();
           for(Edge<V> e: listOfEdges){
               double xCoord = Math.pow(e.source.posX - e.target.posX,2);
               double yCoord = Math.pow(e.source.posY - e.target.posY,2);
               e.distance = Math.sqrt(xCoord + yCoord);
           }
      }
    }
    
    // Part 2
    public void doDijkstra(V s) {
      if(!vertices.containsKey(s)){
          System.out.println("Vertex not present in map");
          return;
      }
      PriorityQueue<VertexWrapper<V>> pq = new PriorityQueue<VertexWrapper<V>>();
      Vertex<V> start = vertices.get(s);
      for(Vertex v: vertices.values()){
          v.cost = Double.MAX_VALUE;
          v.backpointer = null;
          v.visited = false;
      }
      start.cost = 0;
      pq.add(new VertexWrapper(start,start.cost));
      while(!pq.isEmpty()){
          VertexWrapper<V> u = pq.poll();
          u.originalVertex.visited = true;
          List<Edge<V>> listOfEdges = u.originalVertex.getEdges();
          
          for(Edge<V> e: listOfEdges){
              if(!e.target.visited){
                  double c = u.cost + e.distance;
                  if(c < e.target.cost){
                      e.target.cost = c;
                      e.target.backpointer = u.originalVertex;
                      pq.add(new VertexWrapper(e.target, c) );
                  }
              }
          }
          
      } 
        
      return; // TODO
    }

    // Part 3
    public List<Edge<V>> getDijkstraPath(V s, V t) {
      doDijkstra(s);
      Vertex<V> target = vertices.get(t);
      List<Edge<V>> path = new LinkedList<Edge<V>>();
      while(target != vertices.get(s)){
          List<Edge<V>> listOfEdges = target.getEdges();
          Edge<V> answer = null;
          for (Edge<V> e: listOfEdges){
              if(e.target == target.backpointer) answer = e;
          }
          path.add(answer);
          target = target.backpointer;
      }
      
      return path; // TODO
    }