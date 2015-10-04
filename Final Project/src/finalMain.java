//
// shaderMain.java
//
// Main class for lighting / shading assignment.
//
// Modified by Ganesh Rajasekharan
//

import java.awt.*;
import java.io.IOException;
import java.nio.*;
import java.awt.event.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class finalMain implements GLEventListener, KeyListener
{

    /**
     * We need four vertex buffers and four element buffers:
     * two for the torus (flat shading and non-flat shading) and
     * two for the teapot (flat shading and non-flat shading).
     *
     * Array layout:
     *         column 0      column 1
     * row 0:  torus flat    torus non-flat
     * row 1:  teapot flat   teapot non-flat
     */
    private int vbuffer[][];
    private int ebuffer[][];
    private int numVerts[][];
    Texture t;
    /**
     * Animation control
     */
    Animator anime;
    boolean animating;
    long connectsize;
    /**
     * Initial animation rotation angles
     */
    float angles[];

    /**
     * Current shader type:  flat vs. non-flat
     */
    int currentShader;

    /**
     * Program IDs - current, and all variants
     */
    public int program;
    public int flat;
    public int phong;
    public int gouraud;

    /**
     * Shape info
     */
    shapes myShape;

    /**
     * Lighting information
     */
    lightingParams myPhong;

    /**
     * Viewing information
     */
    viewParams myView;

    /**
     * My canvas
     */
    GLCanvas myCanvas;

    /**
     * Constructor
     */
    public finalMain( GLCanvas G )
    {
        vbuffer = new int[12][2];
        ebuffer = new int[12][2];
        numVerts = new int[12][2];

        angles = new float[12];

        animating = false;
        currentShader = shapes.SHADE_FLAT;

        for(int i = 0; i < 12 ; i++){
        	angles[i] = 0.0f;
        }
        myCanvas = G;

        // Initialize lighting and view
        myPhong = new lightingParams();
        myView = new viewParams();

        // Set up event listeners
        G.addGLEventListener (this);
        G.addKeyListener (this);
    }

    private void errorCheck (GL2 gl2)
    {
        int code = gl2.glGetError();
        if (code == GL.GL_NO_ERROR)
            System.err.println ("All is well");
        else
            System.err.println ("Problem - error code : " + code);

    }
    


    /**
     * Simple animate function
     */
    public void animate() {

    	
        angles[shapes.OBJ_STOMACH]  += 2;
        angles[shapes.OBJ_HEAD] += 2;
        angles[shapes.OBJ_NOSE] += 2;
        angles[shapes.OBJ_LEFT_HAND] += 2;
        angles[shapes.OBJ_RIGHT_HAND] += 2;
        angles[shapes.OBJ_LEFT_EYE] += 2;
        angles[shapes.OBJ_RIGHT_EYE] += 2;
        angles[shapes.OBJ_MOUTH1] += 2;
        angles[shapes.OBJ_MOUTH2] += 2;
        angles[shapes.OBJ_MOUTH3] += 2;
        angles[shapes.OBJ_BUTTON1] += 2;
        angles[shapes.OBJ_BUTTON2] += 2;
        
    }

    /**
     * Called by the drawable to initiate OpenGL rendering by the client.
     */
    public void display(GLAutoDrawable drawable)
    {
        // get GL
        GL2 gl2 = (drawable.getGL()).getGL2();

        // clear and draw params..
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

        // use the correct program
        gl2.glUseProgram( program );

        // set up Phong illumination
        myPhong.setUpPhong( program, gl2 );

        // set up viewing and projection parameters
        myView.setUpFrustum( program, gl2 );

        // set up the camera
        myView.setUpCamera( program, gl2,
            -3.0f, 0.0f, 15.5f,
            0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );
       
		//loadTexture("Snow.jpg");
		//makeTexture(program,gl2,shapes.OBJ_STOMACH);
			
		
       int boolREF = 0;
   	float[] color = new float[4];
   	int sc;
     
   
   	color[0] = 1.0f;
   	color[1] = 1.0f;
   	color[2] = 1.0f;
   	color[3] = 1.0f;
    sc = gl2.glGetUniformLocation(program, "shapeColor" );
   	gl2.glUniform4fv(sc, 1, color, 0);
        // set up transformations for the OBJ_STOMACH
        myView.setUpTransforms( program, gl2,
            2.0f, 2.0f, 2.0f,
            angles[shapes.OBJ_STOMACH],
            angles[shapes.OBJ_STOMACH],
            angles[shapes.OBJ_STOMACH],
            -1.8f, -2.5f, 0.0f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_STOMACH, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_STOMACH][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );


        // set up transformations for the OBJ_HEAD
        
       // makeTexture(program,gl2,shapes.OBJ_HEAD);
        myView.setUpTransforms( program, gl2,
            1.4f, 1.4f, 1.4f,
            angles[shapes.OBJ_HEAD],
            angles[shapes.OBJ_HEAD],
            angles[shapes.OBJ_HEAD],
            -1.3f, -0.1f, 0.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_HEAD, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_HEAD][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
     // set up transformations for the OBJ_NOSE
     
       	color[0] = 1.0f;
       	color[1] = 0.0f;
       	color[2] = 0.0f;
       	color[3] = 1.0f;
       	sc = gl2.glGetUniformLocation(program, "shapeColor" );
    	gl2.glUniform4fv(sc, 1, color, 0);
       //makeTexture(program,gl2,shapes.OBJ_NOSE);
        myView.setUpTransforms( program, gl2,
            0.4f, 0.5f, 0.5f,
            30.0f,
            -90.0f,
            -90.0f,
            -0.2f, 0.6f, 2.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_NOSE, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_NOSE][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
     // set up transformations for the OBJ_LEFT_HAND
       // makeTexture(program,gl2,shapes.OBJ_LEFT_HAND);
   
       	color[0] = 0.80f;
       	color[1] = 0.4f;
       	color[2] = 0.2f;
       	color[3] = 1.0f;
       	sc = gl2.glGetUniformLocation(program, "shapeColor" );
    	gl2.glUniform4fv(sc, 1, color, 0);
       //makeTexture(program,gl2,shapes.OBJ_NOSE);
        myView.setUpTransforms( program, gl2,
            1.0f, 1.0f, 1.0f,
            angles[shapes.OBJ_LEFT_HAND],
            angles[shapes.OBJ_LEFT_HAND],
            45.0f,
            -1.6f, -0.2f, 1.8f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_LEFT_HAND, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_LEFT_HAND][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
        
     // set up transformations for the OBJ_RIGHT_HAND
       // makeTexture(program,gl2,shapes.OBJ_RIGHT_HAND);
        myView.setUpTransforms( program, gl2,
            1.0f, 1.0f, 1.0f,
            angles[shapes.OBJ_RIGHT_HAND],
            angles[shapes.OBJ_RIGHT_HAND],
            -45.0f,
            1.4f, -0.2f, 1.6f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_RIGHT_HAND, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_RIGHT_HAND][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
     // set up transformations for the OBJ_LEFT_EYE
       // makeTexture(program,gl2,shapes.OBJ_LEFT_EYE);
      
       	color[0] = 0.0f;
       	color[1] = 0.0f;
       	color[2] = 0.0f;
       	color[3] = 1.0f;
       	sc = gl2.glGetUniformLocation(program, "shapeColor" );
    	gl2.glUniform4fv(sc, 1, color, 0);
        myView.setUpTransforms( program, gl2,
            0.7f, 0.7f, 0.7f,
            90.0f,
            angles[shapes.OBJ_LEFT_EYE],
            angles[shapes.OBJ_LEFT_EYE],
            -0.8f, 0.8f, 2.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_LEFT_EYE, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_LEFT_EYE][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
     // set up transformations for the OBJ_RIGHT_EYE
       // makeTexture(program,gl2,shapes.OBJ_RIGHT_EYE);
        myView.setUpTransforms( program, gl2,
        		0.7f, 0.7f, 0.7f,
                90.0f,
                angles[shapes.OBJ_LEFT_EYE],
                angles[shapes.OBJ_LEFT_EYE],
            0.4f, .8f, 2.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_RIGHT_EYE, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_RIGHT_EYE][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
     // set up transformations for the OBJ_MOUTH1
        myView.setUpTransforms( program, gl2,
        		0.0f, 0.0f, 0.0f,
        		90.0f,
            angles[shapes.OBJ_MOUTH1],
            angles[shapes.OBJ_MOUTH1],
            -0.8f, 0.3f, 5.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_MOUTH1, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_MOUTH1][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
        
     // set up transformations for the OBJ_MOUTH2
        myView.setUpTransforms( program, gl2,
        		0.0f, 0.0f, 0.0f,
        		90.0f,
            angles[shapes.OBJ_MOUTH2],
            angles[shapes.OBJ_MOUTH2],
            1.5f, 0.5f, -1.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_MOUTH2, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_MOUTH2][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
        
     // set up transformations for the OBJ_MOUTH3
        myView.setUpTransforms( program, gl2,
        		0.0f, 0.0f, 0.0f,
        		90.0f,
            angles[shapes.OBJ_MOUTH3],
            angles[shapes.OBJ_MOUTH3],
            0.4f, 0.3f, 5.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_MOUTH3, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_MOUTH3][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
     // set up transformations for the OBJ_BUTTON1
        myView.setUpTransforms( program, gl2,
        		0.0f, 0.0f, 0.0f,
            angles[shapes.OBJ_BUTTON1],
            angles[shapes.OBJ_BUTTON1],
            angles[shapes.OBJ_BUTTON1],
            1.5f, 0.5f, -1.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_BUTTON1, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_BUTTON1][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
        
     // set up transformations for the OBJ_BUTTON2
        myView.setUpTransforms( program, gl2,
        		0.0f, 0.0f, 0.0f,
            angles[shapes.OBJ_BUTTON2],
            angles[shapes.OBJ_BUTTON2],
            angles[shapes.OBJ_BUTTON2],
            1.5f, 0.5f, -1.5f
        );

        // draw it
        selectBuffers( gl2, shapes.OBJ_BUTTON2, currentShader );
        gl2.glDrawElements( GL.GL_TRIANGLES,
            numVerts[shapes.OBJ_BUTTON2][currentShader],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
        
        

        // perform any required animation for the next time
        if( animating ) {
            animate();
        }
    }
    
    
    
    
    
    
    
    

    /**
     * Notifies the listener to perform the release of all OpenGL
     * resources per GLContext, such as memory buffers and GLSL
     * programs.
     */
    public void dispose(GLAutoDrawable drawable)
    {
    }

    /**
     * Verify shader creation
     */
    private void checkShaderError( shaderSetup myShaders, int program,
        String which )
    {
        if( program == 0 ) {
            System.err.println( "Error setting " + which +
                " shader - " +
                myShaders.errorString(myShaders.shaderErrorCode)
            );
            System.exit( 1 );
        }
    }

    /**
     * Called by the drawable immediately after the OpenGL context is
     * initialized.
     */
    public void init(GLAutoDrawable drawable)
    {
        // get the gl object
        GL2 gl2 = drawable.getGL().getGL2();

        // create the Animator now that we have the drawable
        anime = new Animator( drawable );

        // Load shaders, verifying each
        shaderSetup myShaders = new shaderSetup();

        flat = myShaders.readAndCompile( gl2, "flat.vert", "flat.frag" );
        checkShaderError( myShaders, flat, "flat" );

        gouraud = myShaders.readAndCompile(gl2,"gouraud.vert","gouraud.frag");
        checkShaderError( myShaders, gouraud, "gouraud" );

        phong = myShaders.readAndCompile( gl2, "phong.vert", "phong.frag" );
        checkShaderError( myShaders, phong, "phong" );

        // Default shader program
        program = flat;

        // Create all four shapes

        createShape( gl2, shapes.OBJ_STOMACH, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_STOMACH, shapes.SHADE_NOT_FLAT );
        
        createShape( gl2, shapes.OBJ_HEAD, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_HEAD, shapes.SHADE_NOT_FLAT );
        
        createShape( gl2, shapes.OBJ_NOSE, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_NOSE, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_LEFT_HAND, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_LEFT_HAND, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_RIGHT_HAND, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_RIGHT_HAND, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_LEFT_EYE, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_LEFT_EYE, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_RIGHT_EYE, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_RIGHT_EYE, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_MOUTH1, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_MOUTH1, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_MOUTH2, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_MOUTH2, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_MOUTH3, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_MOUTH3, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_BUTTON1, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_BUTTON1, shapes.SHADE_NOT_FLAT );

        createShape( gl2, shapes.OBJ_BUTTON2, shapes.SHADE_FLAT );
        createShape( gl2, shapes.OBJ_BUTTON2, shapes.SHADE_NOT_FLAT );

        
        
        

        // Other GL initialization
        gl2.glEnable( GL.GL_DEPTH_TEST );
        gl2.glEnable( GL.GL_CULL_FACE );
        gl2.glCullFace(  GL.GL_BACK );
        gl2.glFrontFace( GL.GL_CCW );
        gl2.glClearColor( 1.0f, 1.0f, 1.0f, 1.0f );
        gl2.glDepthFunc( GL.GL_LEQUAL );
        gl2.glClearDepth( 1.0f );
    }


    /**
     * Called by the drawable during the first repaint after the component
     * has been resized.
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                     int height)
    {
    }


    /**
     * Create vertex and element buffers for a shape
     */
    public void createShape(GL2 gl2, int obj, int flat )
    {
        // clear the old shape
        myShape = new shapes();

        // make the shape
        myShape.makeShape( obj, flat );

        // save the vertex count
        numVerts[obj][flat] = myShape.nVertices();

        // get the vertices
        Buffer points = myShape.getVertices();
        long dataSize = myShape.nVertices() * 4l * 4l;

        // get the normals
        Buffer normals = myShape.getNormals();
        long ndataSize = myShape.nVertices() * 3l * 4l;

        // get the element data
        Buffer elements = myShape.getElements();
        long edataSize = myShape.nVertices() * 2l;
        
    	Buffer tData;
		long texSize;
       
        // generate the vertex buffer
        
        int bf[] = new int[1];
        switch(obj){
        	case shapes.OBJ_STOMACH:
        		
        		tData = myShape.getUV();
        		texSize = myShape.nVertices() * 2l * 4l;
        		
        	
        		connectsize = ndataSize;
        		gl2.glGenBuffers( 1, bf, 0 );
        		vbuffer[obj][flat] = bf[0];
        		gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj][flat] );
        		gl2.glBufferData( GL.GL_ARRAY_BUFFER, dataSize + ndataSize + texSize, null,
        				GL.GL_STATIC_DRAW );
        		gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, points );
        		gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, ndataSize,
        				normals );
        		gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, dataSize + ndataSize, texSize, tData);

        		// generate the element buffer
        		gl2.glGenBuffers (1, bf, 0);
        		ebuffer[obj][flat] = bf[0];
        		gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat] );
        		gl2.glBufferData( GL.GL_ELEMENT_ARRAY_BUFFER, edataSize, elements,
        				GL.GL_STATIC_DRAW );
        		break;
        	case shapes.OBJ_HEAD:
        		
        	
        		tData = myShape.getUV();
        		texSize = myShape.nVertices() * 2l * 4l;
        		
        	
        		connectsize = ndataSize;
        		gl2.glGenBuffers( 1, bf, 0 );
        		vbuffer[obj][flat] = bf[0];
        		gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj][flat] );
        		gl2.glBufferData( GL.GL_ARRAY_BUFFER, dataSize + ndataSize + texSize, null,
        				GL.GL_STATIC_DRAW );
        		gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, points );
        		gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, ndataSize,
        				normals );
        		gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, dataSize + ndataSize, texSize, tData);

        		// generate the element buffer
        		gl2.glGenBuffers (1, bf, 0);
        		ebuffer[obj][flat] = bf[0];
        		gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat] );
        		gl2.glBufferData( GL.GL_ELEMENT_ARRAY_BUFFER, edataSize, elements,
        				GL.GL_STATIC_DRAW );
        		break;
        	default:
        		
        		gl2.glGenBuffers( 1, bf, 0 );
                vbuffer[obj][flat] = bf[0];
                gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj][flat] );
                gl2.glBufferData( GL.GL_ARRAY_BUFFER, dataSize + ndataSize, null,
                GL.GL_STATIC_DRAW );
                gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, points );
                gl2.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, ndataSize,
                normals );

                // generate the element buffer
                gl2.glGenBuffers (1, bf, 0);
                ebuffer[obj][flat] = bf[0];
                gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat] );
                gl2.glBufferData( GL.GL_ELEMENT_ARRAY_BUFFER, edataSize, elements,
                    GL.GL_STATIC_DRAW );
    
        }
    }

    /**
     * Bind the correct vertex and element buffers
     *
     * Assumes the correct shader program has already been enabled
     */
    private void selectBuffers( GL2 gl2, int obj, int flat )
    {
        // bind the buffers
        gl2.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj][flat] );
        gl2.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj][flat] );

        // calculate the number of bytes of vertex data
        long dataSize = numVerts[obj][flat] * 4l * 4l;

        // set up the vertex attribute variables
        int vPosition = gl2.glGetAttribLocation( program, "vPosition" );
        gl2.glEnableVertexAttribArray( vPosition );
        gl2.glVertexAttribPointer( vPosition, 4, GL.GL_FLOAT, false,
                                       0, 0l );
        int vNormal = gl2.glGetAttribLocation( program, "vNormal" );
        gl2.glEnableVertexAttribArray( vNormal );
        gl2.glVertexAttribPointer( vNormal, 3, GL.GL_FLOAT, false,
                                   0, dataSize );
        int t = gl2.glGetAttribLocation( program, "vTexture");
        gl2.glEnableVertexAttribArray( t );
        gl2.glVertexAttribPointer( t, 3, GL.GL_FLOAT, false,
                                   0, dataSize + connectsize );

    }

    /**
     * Because I am a Key Listener...we'll only respond to key presses
     */
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();

        // Respond appropriately
        switch( key ) {

            case '1':    // flat shading
                program = flat;
                currentShader = shapes.SHADE_FLAT;
                break;

            case '2':    // Gouraud shading
                program = gouraud;
                currentShader = shapes.SHADE_NOT_FLAT;
                break;

            case '3':    // phong shading
                program = phong;
                currentShader = shapes.SHADE_NOT_FLAT;
                break;

            case 'a':    // animate
                animating = true;
                anime.start();
                break;

            case 's':    // stop animating
                animating = false;
                anime.stop();
                break;

            case 'q': case 'Q':
                System.exit( 0 );
                break;
        }

        // do a redraw
        myCanvas.display();
    }


    /**
     * main program
     */
    public static void main(String [] args)
    {
        // GL setup
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        // create your tessMain
        finalMain myMain = new finalMain(canvas);


        Frame frame = new Frame("CG - Shading Assignment");
        frame.setSize(600, 600);
        frame.add(canvas);
        frame.setVisible(true);

        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
