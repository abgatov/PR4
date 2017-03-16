# Updates:

@Override
	public String toString()
	{
		String s = "";

		for(int i = -1; i <= row; i++)
		{
      if ((i == -1) || (i == (row))) {s += "|"}
      else 
      {
        for(int j = 0; j <= col; j++)
		    {
          if (j == -1 || j == (col)) {s += "="}
          else{
		   	    if(carInfo[i][j] != null)
		   	    {
		   	      s += carInfo[i][j].getSymbol();
		   	    }
		   	    else if(obstacleInfo[i][j] != null)
		        {
		        s += obstacleInfo[i][j].getSymbol();
		        }
		  	    else
		   	    {
		   	      s += " ";
		   	    }
          }
        }
		  }
    }
  }
