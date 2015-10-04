//
//  shapes.java
//
//  Students should not be modifying this file.
//
//  @author Vasudev

//  Modified by Ganesh Rajasekharan
//

public class shapes extends simpleShape {

	/**
	 * Object selection variables
	 */
	public static final int OBJ_STOMACH = 0;
	public static final int OBJ_HEAD = 1;
	public static final int OBJ_NOSE = 2;

	public static final int OBJ_LEFT_HAND = 3;
	public static final int OBJ_RIGHT_HAND = 4;

	public static final int OBJ_LEFT_EYE = 5;
	public static final int OBJ_RIGHT_EYE = 6;

	public static final int OBJ_MOUTH1 = 7;    
	public static final int OBJ_MOUTH2 = 8;
	public static final int OBJ_MOUTH3 = 9;

	public static final int OBJ_BUTTON1 = 10;
	public static final int OBJ_BUTTON2 = 11;





	/**
	 * Shading selection variables
	 */
	public static final int SHADE_FLAT = 0;
	public static final int SHADE_NOT_FLAT = 1;

	/**
	 * Store the coordinates into the following datatypes.
	 * 
	 */
	private float[] stomachVertices;    
	private float[] stomachNormals;
	private int[] stomachElements;
	private int[] stomachNormalIndices;


	private float[] headVertices;    
	private float[] headNormals;
	private int[] headElements;
	private int[] headNormalIndices;

	private float[] noseVertices;    
	private float[] noseNormals;
	private int[] noseElements;
	private int[] noseNormalIndices;


	private float[] leftHandVertices;    
	private float[] leftHandNormals;
	private int[] leftHandElements;
	private int[] leftHandNormalIndices;

	private float[] rightHandVertices;    
	private float[] rightHandNormals;
	private int[] rightHandElements;
	private int[] rightHandNormalIndices;


	private float[] eyeLeftVertices;    
	private float[] eyeLeftNormals;
	private int[] eyeLeftElements;
	private int[] eyeLeftNormalIndices;


	private float[] eyeRightVertices;    
	private float[] eyeRightNormals;
	private int[] eyeRightElements;
	private int[] eyeRightNormalIndices;


	private float[] mouth1Vertices;    
	private float[] mouth1Normals;
	private int[] mouth1Elements;
	private int[] mouth1NormalIndices;


	private float[] mouth2Vertices;    
	private float[] mouth2Normals;
	private int[] mouth2Elements;
	private int[] mouth2NormalIndices;


	private float[] mouth3Vertices;    
	private float[] mouth3Normals;
	private int[] mouth3Elements;
	private int[] mouth3NormalIndices;

	private float[] button1Vertices;    
	private float[] button1Normals;
	private int[] button1Elements;
	private int[] button1NormalIndices;


	private float[] button2Vertices;    
	private float[] button2Normals;
	private int[] button2Elements;
	private int[] button2NormalIndices;


	private float[] vertexTexture;
	private int[] faceTexture;





	/**
	 * Constructor
	 */
	public shapes() {

		//set the stomach head nose vertices of the snowman.
		stomachVertices = Coordinates.getStomachVertices();
		stomachNormals = Coordinates.getStomachNormals();
		stomachElements = Coordinates.getStomachElements();
		stomachNormalIndices = Coordinates.getStomachNormalIndices();

		headVertices = Coordinates.getStomachVertices();
		headNormals = Coordinates.getStomachNormals();
		headElements = Coordinates.getStomachElements();
		headNormalIndices = Coordinates.getStomachNormalIndices();

		noseVertices = Coordinates.getNoseVertices();
		noseNormals = Coordinates.getNoseNormals();
		noseElements = Coordinates.getNoseElements();
		noseNormalIndices = Coordinates.getNoseNormalIndices();

		leftHandVertices = Coordinates.getLeftHandVertices();
		leftHandNormals = Coordinates.getLeftHandNormals();
		leftHandElements = Coordinates.getLeftHandElements();
		leftHandNormalIndices = Coordinates.getLeftHandNormalIndices();

		rightHandVertices = Coordinates.getLeftHandVertices();
		rightHandNormals = Coordinates.getLeftHandNormals();
		rightHandElements = Coordinates.getLeftHandElements();
		rightHandNormalIndices = Coordinates.getLeftHandNormalIndices();



		eyeLeftVertices = Coordinates.getRoundVertices();
		eyeLeftNormals = Coordinates.getRoundNormals();
		eyeLeftElements = Coordinates.getRoundElements();
		eyeLeftNormalIndices = Coordinates.getRoundNormalIndices();


		eyeRightVertices = Coordinates.getRoundVertices();
		eyeRightNormals = Coordinates.getRoundNormals();
		eyeRightElements = Coordinates.getRoundElements();
		eyeRightNormalIndices = Coordinates.getRoundNormalIndices();



		mouth1Vertices = Coordinates.getRoundVertices();
		mouth1Normals = Coordinates.getRoundNormals();
		mouth1Elements = Coordinates.getRoundElements();
		mouth1NormalIndices = Coordinates.getRoundNormalIndices();



		mouth2Vertices = Coordinates.getRoundVertices();
		mouth2Normals = Coordinates.getRoundNormals();
		mouth2Elements = Coordinates.getRoundElements();
		mouth2NormalIndices = Coordinates.getRoundNormalIndices();


		mouth3Vertices = Coordinates.getRoundVertices();
		mouth3Normals = Coordinates.getRoundNormals();
		mouth3Elements = Coordinates.getRoundElements();
		mouth3NormalIndices = Coordinates.getRoundNormalIndices();




		button1Vertices = Coordinates.getRoundVertices();
		button1Normals = Coordinates.getRoundNormals();
		button1Elements = Coordinates.getRoundElements();
		button1NormalIndices = Coordinates.getRoundNormalIndices();


		button2Vertices = Coordinates.getRoundVertices();
		button2Normals = Coordinates.getRoundNormals();
		button2Elements = Coordinates.getRoundElements();
		button2NormalIndices = Coordinates.getRoundNormalIndices();


		vertexTexture  = Coordinates.getTextureVertices();;
		faceTexture  = Coordinates.getTextureFaces();;



	}


	//generate the mesh for the objects
	private void makeStomach( int shadingType ) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < stomachElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * stomachElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * stomachElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * stomachElements[i + 2]; // slots 6, 7, 8
				int texvertex1 = 2 * faceTexture[i];
				int texvertex2 = 2 * faceTexture[i+1];
				int texvertex3 = 2 * faceTexture[i+2];
				addTriangleT(
						stomachVertices[vertex1 + 0], stomachVertices[vertex1 + 1],
						stomachVertices[vertex1 + 2], vertexTexture[ texvertex1 + 0], vertexTexture[ texvertex1 + 1], 
						stomachVertices[vertex2 + 0], stomachVertices[vertex2 + 1],
						stomachVertices[vertex2 + 2], vertexTexture[ texvertex2 + 0], vertexTexture[ texvertex2 + 1],
						stomachVertices[vertex3 + 0], stomachVertices[vertex3 + 1],
						stomachVertices[vertex3 + 2], vertexTexture[ texvertex3 + 0], vertexTexture[ texvertex3 + 1]
						);
			}

		} else {

			for( int i = 0; i < stomachElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * stomachElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * stomachElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * stomachElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * stomachNormalIndices[i];
				int normal2 = 3 * stomachNormalIndices[i + 1];
				int normal3 = 3 * stomachNormalIndices[i + 2];

				int texvertex1 = 2 * faceTexture[i];
				int texvertex2 = 2 * faceTexture[i+1];
				int texvertex3 = 2 * faceTexture[i+2];

				addTriangleNT(
						stomachVertices[vertex1 + 0], stomachVertices[vertex1 + 1],
						stomachVertices[vertex1 + 2], vertexTexture[ texvertex1 + 0], vertexTexture[ texvertex1 + 1], 
						stomachVertices[vertex2 + 0], stomachVertices[vertex2 + 1],
						stomachVertices[vertex2 + 2], vertexTexture[ texvertex2 + 0], vertexTexture[ texvertex2 + 1],
						stomachVertices[vertex3 + 0], stomachVertices[vertex3 + 1],
						stomachVertices[vertex3 + 2], vertexTexture[ texvertex3 + 0], vertexTexture[ texvertex3 + 1],

						headNormals[normal1 + 0], headNormals[normal1 + 1],
						headNormals[normal1 + 2],
						headNormals[normal2 + 0], headNormals[normal2 + 1],
						headNormals[normal2 + 2],
						headNormals[normal3 + 0], headNormals[normal3 + 1],
						headNormals[normal3 + 2]
						);
			}
		}
	}

	private void makeHead( int shadingType ) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < headElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * headElements[i]; // slots 0, 1, 2
				int vertex2 = 3 * headElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * headElements[i + 2]; // slots 6, 7, 8
				int texvertex1 = 2 * faceTexture[i];
				int texvertex2 = 2 * faceTexture[i+1];
				int texvertex3 = 2 * faceTexture[i+2];
				addTriangleT(
						headVertices[vertex1 + 0], headVertices[vertex1 + 1],
						headVertices[vertex1 + 2], vertexTexture[ texvertex1 + 0], vertexTexture[ texvertex1 + 1], 
						headVertices[vertex2 + 0], headVertices[vertex2 + 1],
						headVertices[vertex2 + 2], vertexTexture[ texvertex2 + 0], vertexTexture[ texvertex2 + 1],
						headVertices[vertex3 + 0], headVertices[vertex3 + 1],
						headVertices[vertex3 + 2], vertexTexture[ texvertex3 + 0], vertexTexture[ texvertex3 + 1]
						);
			}

		} else {

			for( int i = 0; i < headElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * headElements[i]; // slots 0, 1, 2
				int vertex2 = 3 * headElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * headElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * headNormalIndices[i];
				int normal2 = 3 * headNormalIndices[i + 1];
				int normal3 = 3 * headNormalIndices[i + 2];
				int texvertex1 = 2 * faceTexture[i];
				int texvertex2 = 2 * faceTexture[i+1];
				int texvertex3 = 2 * faceTexture[i+2];

				addTriangleNT(
						headVertices[vertex1 + 0], headVertices[vertex1 + 1],
						headVertices[vertex1 + 2], vertexTexture[ texvertex1 + 0], vertexTexture[ texvertex1 + 1], 
						headVertices[vertex2 + 0], headVertices[vertex2 + 1],
						headVertices[vertex2 + 2], vertexTexture[ texvertex2 + 0], vertexTexture[ texvertex2 + 1],
						headVertices[vertex3 + 0], headVertices[vertex3 + 1],
						headVertices[vertex3 + 2], vertexTexture[ texvertex3 + 0], vertexTexture[ texvertex3 + 1],

						headNormals[normal1 + 0], headNormals[normal1 + 1],
						headNormals[normal1 + 2],
						headNormals[normal2 + 0], headNormals[normal2 + 1],
						headNormals[normal2 + 2],
						headNormals[normal3 + 0], headNormals[normal3 + 1],
						headNormals[normal3 + 2]
						);
			}
		}
	}


	private void makeNose( int shadingType ) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < noseElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * noseElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * noseElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * noseElements[i + 2]; // slots 6, 7, 8

				addTriangle(
						noseVertices[vertex1 + 0], noseVertices[vertex1 + 1],
						noseVertices[vertex1 + 2],
						noseVertices[vertex2 + 0], noseVertices[vertex2 + 1],
						noseVertices[vertex2 + 2],
						noseVertices[vertex3 + 0], noseVertices[vertex3 + 1],
						noseVertices[vertex3 + 2]
						);
			}

		} else {

			for( int i = 0; i < noseElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * noseElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * noseElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * noseElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * noseNormalIndices[i];
				int normal2 = 3 * noseNormalIndices[i + 1];
				int normal3 = 3 * noseNormalIndices[i + 2];

				addTriangleN(
						noseVertices[vertex1 + 0], noseVertices[vertex1 + 1],
						noseVertices[vertex1 + 2],
						noseVertices[vertex2 + 0], noseVertices[vertex2 + 1],
						noseVertices[vertex2 + 2],
						noseVertices[vertex3 + 0], noseVertices[vertex3 + 1],
						noseVertices[vertex3 + 2],

						noseNormals[normal1 + 0], noseNormals[normal1 + 1],
						noseNormals[normal1 + 2],
						noseNormals[normal2 + 0], noseNormals[normal2 + 1],
						noseNormals[normal2 + 2],
						noseNormals[normal3 + 0], noseNormals[normal3 + 1],
						noseNormals[normal3 + 2]
						);

			}
		}
	}



	private void makeRightHand(int shadingType) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < rightHandElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * rightHandElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * rightHandElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * rightHandElements[i + 2]; // slots 6, 7, 8

				addTriangle(
						rightHandVertices[vertex1 + 0], rightHandVertices[vertex1 + 1],
						rightHandVertices[vertex1 + 2],
						rightHandVertices[vertex2 + 0], rightHandVertices[vertex2 + 1],
						rightHandVertices[vertex2 + 2],
						rightHandVertices[vertex3 + 0], rightHandVertices[vertex3 + 1],
						rightHandVertices[vertex3 + 2]
						);
			}

		} else {

			for( int i = 0; i < rightHandElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * rightHandElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * rightHandElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * rightHandElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * rightHandNormalIndices[i];
				int normal2 = 3 * rightHandNormalIndices[i + 1];
				int normal3 = 3 * rightHandNormalIndices[i + 2];

				addTriangleN(
						rightHandVertices[vertex1 + 0], rightHandVertices[vertex1 + 1],
						rightHandVertices[vertex1 + 2],
						rightHandVertices[vertex2 + 0], rightHandVertices[vertex2 + 1],
						rightHandVertices[vertex2 + 2],
						rightHandVertices[vertex3 + 0], rightHandVertices[vertex3 + 1],
						rightHandVertices[vertex3 + 2],

						rightHandNormals[normal1 + 0], rightHandNormals[normal1 + 1],
						rightHandNormals[normal1 + 2],
						rightHandNormals[normal2 + 0], rightHandNormals[normal2 + 1],
						rightHandNormals[normal2 + 2],
						rightHandNormals[normal3 + 0], rightHandNormals[normal3 + 1],
						rightHandNormals[normal3 + 2]
						);

			}
		}
	}



	private void makeLeftHand(int shadingType) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < leftHandElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * leftHandElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * leftHandElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * leftHandElements[i + 2]; // slots 6, 7, 8

				addTriangle(
						leftHandVertices[vertex1 + 0], leftHandVertices[vertex1 + 1],
						leftHandVertices[vertex1 + 2],
						leftHandVertices[vertex2 + 0], leftHandVertices[vertex2 + 1],
						leftHandVertices[vertex2 + 2],
						leftHandVertices[vertex3 + 0], leftHandVertices[vertex3 + 1],
						leftHandVertices[vertex3 + 2]
						);
			}

		} else {

			for( int i = 0; i < leftHandElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * leftHandElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * leftHandElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * leftHandElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * leftHandNormalIndices[i];
				int normal2 = 3 * leftHandNormalIndices[i + 1];
				int normal3 = 3 * leftHandNormalIndices[i + 2];

				addTriangleN(
						leftHandVertices[vertex1 + 0], leftHandVertices[vertex1 + 1],
						leftHandVertices[vertex1 + 2],
						leftHandVertices[vertex2 + 0], leftHandVertices[vertex2 + 1],
						leftHandVertices[vertex2 + 2],
						leftHandVertices[vertex3 + 0], leftHandVertices[vertex3 + 1],
						leftHandVertices[vertex3 + 2],

						leftHandNormals[normal1 + 0], leftHandNormals[normal1 + 1],
						leftHandNormals[normal1 + 2],
						leftHandNormals[normal2 + 0], leftHandNormals[normal2 + 1],
						leftHandNormals[normal2 + 2],
						leftHandNormals[normal3 + 0], leftHandNormals[normal3 + 1],
						leftHandNormals[normal3 + 2]
						);

			}
		}

	}



	private void makeEyeLeft( int shadingType ) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < eyeLeftElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * eyeLeftElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * eyeLeftElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * eyeLeftElements[i + 2]; // slots 6, 7, 8

				addTriangle(
						eyeLeftVertices[vertex1 + 0], eyeLeftVertices[vertex1 + 1],
						eyeLeftVertices[vertex1 + 2],
						eyeLeftVertices[vertex2 + 0], eyeLeftVertices[vertex2 + 1],
						eyeLeftVertices[vertex2 + 2],
						eyeLeftVertices[vertex3 + 0], eyeLeftVertices[vertex3 + 1],
						eyeLeftVertices[vertex3 + 2]
						);
			}

		} else {

			for( int i = 0; i < eyeLeftElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * eyeLeftElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * eyeLeftElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * eyeLeftElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * eyeLeftNormalIndices[i];
				int normal2 = 3 * eyeLeftNormalIndices[i + 1];
				int normal3 = 3 * eyeLeftNormalIndices[i + 2];

				addTriangleN(
						eyeLeftVertices[vertex1 + 0], eyeLeftVertices[vertex1 + 1],
						eyeLeftVertices[vertex1 + 2],
						eyeLeftVertices[vertex2 + 0], eyeLeftVertices[vertex2 + 1],
						eyeLeftVertices[vertex2 + 2],
						eyeLeftVertices[vertex3 + 0], eyeLeftVertices[vertex3 + 1],
						eyeLeftVertices[vertex3 + 2],

						eyeLeftNormals[normal1 + 0], eyeLeftNormals[normal1 + 1],
						eyeLeftNormals[normal1 + 2],
						eyeLeftNormals[normal2 + 0], eyeLeftNormals[normal2 + 1],
						eyeLeftNormals[normal2 + 2],
						eyeLeftNormals[normal3 + 0], eyeLeftNormals[normal3 + 1],
						eyeLeftNormals[normal3 + 2]
						);

			}
		}
	}




	private void makeEyeRight( int shadingType ) {

		if( shadingType == shapes.SHADE_FLAT ) {

			for( int i = 0; i < eyeRightElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * eyeRightElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * eyeRightElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * eyeRightElements[i + 2]; // slots 6, 7, 8

				addTriangle(
						eyeRightVertices[vertex1 + 0], eyeRightVertices[vertex1 + 1],
						eyeRightVertices[vertex1 + 2],
						eyeRightVertices[vertex2 + 0], eyeRightVertices[vertex2 + 1],
						eyeRightVertices[vertex2 + 2],
						eyeRightVertices[vertex3 + 0], eyeRightVertices[vertex3 + 1],
						eyeRightVertices[vertex3 + 2]
						);
			}

		} else {

			for( int i = 0; i < eyeRightElements.length - 2; i += 3 ) {

				// calculate the base indices of the three vertices
				int vertex1 = 3 * eyeRightElements[i];     // slots 0, 1, 2
				int vertex2 = 3 * eyeRightElements[i + 1]; // slots 3, 4, 5
				int vertex3 = 3 * eyeRightElements[i + 2]; // slots 6, 7, 8

				int normal1 = 3 * eyeRightNormalIndices[i];
				int normal2 = 3 * eyeRightNormalIndices[i + 1];
				int normal3 = 3 * eyeRightNormalIndices[i + 2];

				addTriangleN(
						eyeRightVertices[vertex1 + 0], eyeRightVertices[vertex1 + 1],
						eyeRightVertices[vertex1 + 2],
						eyeRightVertices[vertex2 + 0], eyeRightVertices[vertex2 + 1],
						eyeRightVertices[vertex2 + 2],
						eyeRightVertices[vertex3 + 0], eyeRightVertices[vertex3 + 1],
						eyeRightVertices[vertex3 + 2],

						eyeRightNormals[normal1 + 0], eyeRightNormals[normal1 + 1],
						eyeRightNormals[normal1 + 2],
						eyeRightNormals[normal2 + 0], eyeRightNormals[normal2 + 1],
						eyeRightNormals[normal2 + 2],
						eyeRightNormals[normal3 + 0], eyeRightNormals[normal3 + 1],
						eyeRightNormals[normal3 + 2]
						);

			}
		}
	}

	public void makeShape( int choice, int shadingType ) {
		if( choice == shapes.OBJ_STOMACH )
			makeStomach( shadingType );

		if( choice == shapes.OBJ_HEAD )
			makeHead( shadingType );

		if( choice == shapes.OBJ_NOSE )
			makeNose(shadingType);


		if( choice == shapes.OBJ_LEFT_HAND )
			makeLeftHand(shadingType);

		if( choice == shapes.OBJ_RIGHT_HAND )
			makeRightHand(shadingType);

		if( choice == shapes.OBJ_LEFT_EYE )
			makeEyeLeft(shadingType);

		if( choice == shapes.OBJ_RIGHT_EYE )
			makeEyeRight(shadingType);
      	
	}
}
