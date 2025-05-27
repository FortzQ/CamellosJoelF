
## CLIENTES.VB
´´´vb
Imports System.Data.SQLite

Public Class ClientesForm

    Private Sub ClientesForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        CargarClientes()
    End Sub

    Private Sub CargarClientes(Optional filtro As String = "")
        lvClientes.Items.Clear()

        Try
            Using conn As SQLiteConnection = ObtenerConexion()
                conn.Open()

                Dim query As String = "SELECT DNI, Nombre, Apellidos, Telefono, Email, Direccion, Codigo_Postal FROM Clientes"
                If Not String.IsNullOrEmpty(filtro) Then
                    query &= " WHERE DNI LIKE @filtro"
                End If

                Using cmd As New SQLiteCommand(query, conn)
                    If Not String.IsNullOrEmpty(filtro) Then
                        cmd.Parameters.AddWithValue("@filtro", "%" & filtro & "%")
                    End If

                    Using reader As SQLiteDataReader = cmd.ExecuteReader()
                        While reader.Read()
                            Dim item As New ListViewItem(reader("DNI").ToString())
                            item.SubItems.Add(reader("Nombre").ToString())
                            item.SubItems.Add(reader("Apellidos").ToString())
                            item.SubItems.Add(reader("Telefono").ToString())
                            item.SubItems.Add(reader("Email").ToString())
                            item.SubItems.Add(reader("Direccion").ToString())
                            item.SubItems.Add(reader("Codigo_Postal").ToString())
                            lvClientes.Items.Add(item)
                        End While
                    End Using
                End Using
            End Using

        Catch ex As Exception
            MessageBox.Show("Error al cargar los clientes: " & ex.Message)
        End Try
    End Sub



    Private Sub txtBuscar_TextChanged(sender As Object, e As EventArgs) Handles txtBuscar.TextChanged
        CargarClientes(txtBuscar.Text.Trim())
    End Sub


    ' --- Menú navegación ---t
    Private Sub MenuInicio_Click(sender As Object, e As EventArgs) Handles menuInicio.Click
        MainForm.Show()
        Me.Close()
    End Sub

    Private Sub MenuCompras_Click(sender As Object, e As EventArgs) Handles menuCompras.Click
        ComprasForm.Show()
        Me.Close()
    End Sub

    Private Sub MenuProveedores_Click(sender As Object, e As EventArgs) Handles menuProveedores.Click
        ProveedoresForm.Show()
        Me.Close()
    End Sub

    Private Sub MenuVehiculos_Click(sender As Object, e As EventArgs) Handles menuVehiculos.Click
        VehiculosForm.Show()
        Me.Close()
    End Sub

    Private Sub MenuVentas_Click(sender As Object, e As EventArgs) Handles menuVentas.Click
        VentasForm.Show()
        Me.Close()
    End Sub

    Private Sub MenuSalir_Click(sender As Object, e As EventArgs) Handles menuSalir.Click
        Application.Exit()
    End Sub

    Private Sub picBuscar_Click(sender As Object, e As EventArgs) Handles picBuscar.Click

    End Sub
End Class
´´´

## CLIENTES DESIGNER
<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class ClientesForm
    Inherits System.Windows.Forms.Form

    Private components As System.ComponentModel.IContainer
    Friend WithEvents picBuscar As System.Windows.Forms.PictureBox

    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(ClientesForm))
        Me.menuStrip = New System.Windows.Forms.MenuStrip()
        Me.menuInicio = New System.Windows.Forms.ToolStripMenuItem()
        Me.menuCompras = New System.Windows.Forms.ToolStripMenuItem()
        Me.menuProveedores = New System.Windows.Forms.ToolStripMenuItem()
        Me.menuVehiculos = New System.Windows.Forms.ToolStripMenuItem()
        Me.menuVentas = New System.Windows.Forms.ToolStripMenuItem()
        Me.menuSalir = New System.Windows.Forms.ToolStripMenuItem()
        Me.lblTitulo = New System.Windows.Forms.Label()
        Me.lvClientes = New System.Windows.Forms.ListView()
        Me.colDNI = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.colNombre = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.colApellidos = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.colTelefono = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.colEmail = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.colDireccion = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.colCodigoPostal = CType(New System.Windows.Forms.ColumnHeader(), System.Windows.Forms.ColumnHeader)
        Me.txtBuscar = New System.Windows.Forms.TextBox()
        Me.picBuscar = New System.Windows.Forms.PictureBox()
        Me.menuStrip.SuspendLayout()
        CType(Me.picBuscar, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'menuStrip
        '
        Me.menuStrip.BackColor = System.Drawing.Color.FromArgb(CType(CType(100, Byte), Integer), CType(CType(100, Byte), Integer), CType(CType(120, Byte), Integer))
        Me.menuStrip.Font = New System.Drawing.Font("Segoe UI", 10.0!, System.Drawing.FontStyle.Bold)
        Me.menuStrip.ForeColor = System.Drawing.Color.White
        Me.menuStrip.GripMargin = New System.Windows.Forms.Padding(2, 2, 0, 2)
        Me.menuStrip.ImageScalingSize = New System.Drawing.Size(32, 32)
        Me.menuStrip.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.menuInicio, Me.menuCompras, Me.menuProveedores, Me.menuVehiculos, Me.menuVentas, Me.menuSalir})
        Me.menuStrip.Location = New System.Drawing.Point(0, 75)
        Me.menuStrip.Name = "menuStrip"
        Me.menuStrip.Padding = New System.Windows.Forms.Padding(9, 2, 0, 2)
        Me.menuStrip.Size = New System.Drawing.Size(2654, 45)
        Me.menuStrip.TabIndex = 0
        Me.menuStrip.Text = "menuStrip"
        '
        'menuInicio
        '
        Me.menuInicio.Name = "menuInicio"
        Me.menuInicio.Size = New System.Drawing.Size(110, 41)
        Me.menuInicio.Text = "Menú"
        '
        'menuCompras
        '
        Me.menuCompras.Name = "menuCompras"
        Me.menuCompras.Size = New System.Drawing.Size(151, 41)
        Me.menuCompras.Text = "Compras"
        '
        'menuProveedores
        '
        Me.menuProveedores.Name = "menuProveedores"
        Me.menuProveedores.Size = New System.Drawing.Size(199, 41)
        Me.menuProveedores.Text = "Proveedores"
        '
        'menuVehiculos
        '
        Me.menuVehiculos.Name = "menuVehiculos"
        Me.menuVehiculos.Size = New System.Drawing.Size(158, 41)
        Me.menuVehiculos.Text = "Vehículos"
        '
        'menuVentas
        '
        Me.menuVentas.Name = "menuVentas"
        Me.menuVentas.Size = New System.Drawing.Size(122, 41)
        Me.menuVentas.Text = "Ventas"
        '
        'menuSalir
        '
        Me.menuSalir.Name = "menuSalir"
        Me.menuSalir.Size = New System.Drawing.Size(94, 41)
        Me.menuSalir.Text = "Salir"
        '
        'lblTitulo
        '
        Me.lblTitulo.Dock = System.Windows.Forms.DockStyle.Top
        Me.lblTitulo.Font = New System.Drawing.Font("Segoe UI", 14.0!, System.Drawing.FontStyle.Bold)
        Me.lblTitulo.ForeColor = System.Drawing.Color.White
        Me.lblTitulo.Location = New System.Drawing.Point(0, 0)
        Me.lblTitulo.Margin = New System.Windows.Forms.Padding(4, 0, 4, 0)
        Me.lblTitulo.Name = "lblTitulo"
        Me.lblTitulo.Size = New System.Drawing.Size(2654, 75)
        Me.lblTitulo.TabIndex = 1
        Me.lblTitulo.Text = "Gestión de Clientes"
        Me.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        '
        'lvClientes
        '
        Me.lvClientes.Anchor = System.Windows.Forms.AnchorStyles.None
        Me.lvClientes.BackColor = System.Drawing.Color.White
        Me.lvClientes.Columns.AddRange(New System.Windows.Forms.ColumnHeader() {Me.colDNI, Me.colNombre, Me.colApellidos, Me.colTelefono, Me.colEmail, Me.colDireccion, Me.colCodigoPostal})
        Me.lvClientes.Font = New System.Drawing.Font("Arial Rounded MT Bold", 7.875!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.lvClientes.FullRowSelect = True
        Me.lvClientes.GridLines = True
        Me.lvClientes.HideSelection = False
        Me.lvClientes.Location = New System.Drawing.Point(81, 251)
        Me.lvClientes.Margin = New System.Windows.Forms.Padding(4)
        Me.lvClientes.Name = "lvClientes"
        Me.lvClientes.Size = New System.Drawing.Size(2495, 851)
        Me.lvClientes.TabIndex = 3
        Me.lvClientes.UseCompatibleStateImageBehavior = False
        Me.lvClientes.View = System.Windows.Forms.View.Details
        '
        'colDNI
        '
        Me.colDNI.Text = "DNI"
        Me.colDNI.Width = 100
        '
        'colNombre
        '
        Me.colNombre.Text = "Nombre"
        Me.colNombre.Width = 120
        '
        'colApellidos
        '
        Me.colApellidos.Text = "Apellidos"
        Me.colApellidos.Width = 105
        '
        'colTelefono
        '
        Me.colTelefono.Text = "Teléfono"
        Me.colTelefono.Width = 100
        '
        'colEmail
        '
        Me.colEmail.Text = "Email"
        Me.colEmail.Width = 180
        '
        'colDireccion
        '
        Me.colDireccion.Text = "Dirección"
        Me.colDireccion.Width = 200
        '
        'colCodigoPostal
        '
        Me.colCodigoPostal.Text = "CP"
        Me.colCodigoPostal.Width = 50
        '
        'txtBuscar
        '
        Me.txtBuscar.Anchor = System.Windows.Forms.AnchorStyles.None
        Me.txtBuscar.Font = New System.Drawing.Font("Segoe UI", 10.0!)
        Me.txtBuscar.Location = New System.Drawing.Point(1016, 158)
        Me.txtBuscar.Margin = New System.Windows.Forms.Padding(4)
        Me.txtBuscar.Name = "txtBuscar"
        Me.txtBuscar.Size = New System.Drawing.Size(767, 43)
        Me.txtBuscar.TabIndex = 2
        '
        'picBuscar
        '
        Me.picBuscar.Anchor = System.Windows.Forms.AnchorStyles.None
        Me.picBuscar.Image = CType(resources.GetObject("picBuscar.Image"), System.Drawing.Image)
        Me.picBuscar.Location = New System.Drawing.Point(944, 167)
        Me.picBuscar.Margin = New System.Windows.Forms.Padding(4)
        Me.picBuscar.Name = "picBuscar"
        Me.picBuscar.Size = New System.Drawing.Size(40, 34)
        Me.picBuscar.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.picBuscar.TabIndex = 0
        Me.picBuscar.TabStop = False
        '
        'ClientesForm
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(192.0!, 192.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi
        Me.AutoScroll = True
        Me.BackColor = System.Drawing.Color.FromArgb(CType(CType(40, Byte), Integer), CType(CType(40, Byte), Integer), CType(CType(60, Byte), Integer))
        Me.ClientSize = New System.Drawing.Size(2654, 1153)
        Me.Controls.Add(Me.picBuscar)
        Me.Controls.Add(Me.txtBuscar)
        Me.Controls.Add(Me.lvClientes)
        Me.Controls.Add(Me.menuStrip)
        Me.Controls.Add(Me.lblTitulo)
        Me.MainMenuStrip = Me.menuStrip
        Me.Margin = New System.Windows.Forms.Padding(4)
        Me.Size = New Size(Screen.PrimaryScreen.WorkingArea.Width, Screen.PrimaryScreen.WorkingArea.Height)
        Me.MaximizeBox = False
        Me.Name = "ClientesForm"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Clientes"
        Me.menuStrip.ResumeLayout(False)
        Me.menuStrip.PerformLayout()
        CType(Me.picBuscar, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents menuStrip As System.Windows.Forms.MenuStrip
    Friend WithEvents menuInicio As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents menuCompras As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents menuProveedores As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents menuVehiculos As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents menuVentas As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents menuSalir As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents lblTitulo As System.Windows.Forms.Label
    Friend WithEvents txtBuscar As System.Windows.Forms.TextBox
    Friend WithEvents lvClientes As System.Windows.Forms.ListView
    Friend WithEvents colDNI As System.Windows.Forms.ColumnHeader
    Friend WithEvents colNombre As System.Windows.Forms.ColumnHeader
    Friend WithEvents colTelefono As System.Windows.Forms.ColumnHeader
    Friend WithEvents colEmail As System.Windows.Forms.ColumnHeader
    Friend WithEvents colApellidos As System.Windows.Forms.ColumnHeader
    Friend WithEvents colDireccion As System.Windows.Forms.ColumnHeader
    Friend WithEvents colCodigoPostal As System.Windows.Forms.ColumnHeader

End Class
